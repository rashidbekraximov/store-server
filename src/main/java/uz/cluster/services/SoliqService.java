package uz.cluster.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import uz.cluster.dao.FacturaDTO;
import uz.cluster.dao.FacturaData;
import uz.cluster.dao.Invoice;
import uz.cluster.dao.RequestDao;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class SoliqService {

    public static final Map<Integer,List<FacturaData>> map = new HashMap<>();


    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void loadFactura(RequestDao requestDao) {
        map.clear();
        List<FacturaData> invoiceList = new ArrayList<>();
        String URL = "https://my.soliq.uz/faktura/uz/" + requestDao.getStatus() + "/getfacturadata";

//        requestDao.setFacturaSessionId("ZmFjdHVyYV8zMDk5MjY0ODVfMDhCODY1MEZBMDk5NEM1RkI4RTk3NkQ5RjNENTU1OUM%3D");
//        requestDao.setRoute("c5ebf7813f227e0f90ef527b1072a24d");
        try {
            // Create HTTP client and POST request
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(URL);

            // Set headers
            post.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
            post.setHeader("Accept-Encoding", "gzip, deflate, br, zstd");
            post.setHeader("Accept-Language", "en-US,en;q=0.9,en-NZ;q=0.8");
            post.setHeader("Content-Type", "application/json; charset=UTF-8");
            post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");
            post.setHeader("X-Requested-With", "XMLHttpRequest");

            // Set cookies
            post.setHeader("Cookie", "route=" +
                    requestDao.getRoute() + ";" +
                    " lang=uz; " +
                    "my.soliq.uz=" +
                    requestDao.getMySoliqUz() + ";" +
                    "ssoid=" +
                    requestDao.getSsoid() + ";" +
                    "FacturaSessionId=" +
                    requestDao.getFacturaSessionId()
            );

            // Create JSON request body
            String jsonRequest = "{\n" +
                    "  \"draw\": 1,\n" +
                    "  \"columns\": [],\n" +
                    "  \"order\": [],\n" +
                    "  \"start\": 0,\n" +
                    "  \"length\": 1000,\n" +
                    "  \"search\": {\n" +
                    "    \"value\": \"\",\n" +
                    "    \"regex\": false\n" +
                    "  },\n" +
                    "  \"AgentFio\": \"\",\n" +
                    "  \"CompanyName\": \"\",\n" +
                    "  \"CompanyTin\": \"\",\n" +
                    "  \"ContractDateFrom\": \"\",\n" +
                    "  \"ContractDateTo\": \"\",\n" +
                    "  \"ContractNo\": \"\",\n" +
                    "  \"EmpowermentDateFrom\": \"\",\n" +
                    "  \"EmpowermentDateTo\": \"\",\n" +
                    "  \"EmpowermentNo\": \"\",\n" +
                    "  \"FacturaDateFrom\": \"\",\n" +
                    "  \"FacturaDateTo\": \"\",\n" +
                    "  \"FacturaNo\": \"\",\n" +
                    "  \"StateId\": \"0\"\n" +
                    "}";
            StringEntity entity = new StringEntity(jsonRequest);
            post.setEntity(entity);

            // Execute request and get response
            HttpResponse response = client.execute(post);
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                String responseString = EntityUtils.toString(responseEntity);
                System.out.println("Response: " + responseString);

                // Parse JSON response to DTO
                ObjectMapper objectMapper = new ObjectMapper();
                FacturaDTO facturaDTO = objectMapper.readValue(responseString, FacturaDTO.class);

                List<Callable<FacturaData>> tasks = new ArrayList<>();

                for (FacturaData facturaData : facturaDTO.getData()) {
                    Callable<FacturaData> task = () -> getTable(facturaData,facturaData.getFacturaId(), requestDao);
                    tasks.add(task);
                }

                // Invoke all tasks and retrieve futures
                List<Future<FacturaData>> futures = executorService.invokeAll(tasks);

                // Collect results from futures
                for (Future<FacturaData> future : futures) {
                    try {
                        FacturaData facturaData = future.get(); // This will block until the task is completed
                        invoiceList.add(facturaData);
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("FacturaDTO: " + facturaDTO);
            }
            map.put(1,invoiceList);
            client.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public FacturaData getTable(FacturaData facturaData,String facturaId,RequestDao requestDao) {
        String url;
        if (requestDao.getStatus().equals("sent")){
            url = "https://my.soliq.uz/faktura/uz/pdf/getfacturafilehtml/" + facturaId + "?type=";
        }else {
            url =  "https://my.soliq.uz/faktura/uz/pdf/getfacturafilehtml/" + facturaId + "?type=buyer";
        }

        try {
            // Fetch the HTML document
            Document doc = Jsoup.connect(url)
                    .header("Accept-Encoding", "gzip, deflate, br, zstd")
                    .header("Accept-Language", "en-US,en;q=0.9,en-NZ;q=0.8")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36")
                    .header("Cookie", "route=" +
                            requestDao.getRoute() + "; " +
                            "lang=uz; " +
                            "my.soliq.uz=" +
                            requestDao.getMySoliqUz() + "; " +
                            "ssoid=" +
                            requestDao.getSsoid() + "; " +
                            "FacturaSessionId=" +
                            requestDao.getFacturaSessionId()
                    )
                    .get();

            // Find the table with id "products"
            Element table = doc.select("table#products").first();

            if (table != null) {
                // Select rows within the table body
                Elements rows = table.select("tbody tr");

                // List to hold Invoice objects
                List<Invoice> invoices = new ArrayList<>();

                // Iterate through each row
                for (Element row : rows) {
                    // Select columns within the row
                    Elements columns = row.select("td");

                    if (columns.size() >= 11) {
                        String productCode = columns.get(2).text().trim();

                        if (facturaData.getFacturaId().equals("65425af44400caa5e5777ba7")){
                            System.out.println("kellli");
                        }
                        if (areAllCharactersNumbers(productCode)){
                            String invoiceNumber = columns.get(0).text().trim();
                            String description = columns.get(4).text().trim();
                            productCode = columns.get(5).text().trim();
                            String unit = columns.get(6).text().trim();
                            String quantity = columns.get(7).text().trim();
                            String price = columns.get(8).text().trim();
                            String deliveryPrice = columns.get(9).text().trim();
                            String taxRate = columns.get(10).text().trim();
                            String taxAmount = "";
                            String totalPrice = facturaData.getPayableTotal();
                            String remarks = "";
                            // Create Invoice object and add to list
                            Invoice invoice = new Invoice(invoiceNumber, description, productCode, unit, quantity,
                                    price, deliveryPrice, taxRate, taxAmount, totalPrice, remarks);
                            invoices.add(invoice);
                            continue;
                        }


                        // Assuming the columns match the structure
                        productCode = columns.get(2).text().trim();
                        String description = columns.get(1).text().trim();

                        String unit = columns.get(3).text().trim();
                        String invoiceNumber = columns.get(0).text().trim();


                        if (areAllCharactersNumbers(unit) || columns.size() == 12){
                            unit = columns.get(4).text().trim();
                            String quantity = columns.get(5).text().trim();
                            String price = columns.get(6).text().trim();
                            String deliveryPrice = columns.get(7).text().trim();
                            String taxRate = columns.get(8).text().trim();
                            String taxAmount = columns.get(9).text().trim();
                            String totalPrice = columns.get(10).text().trim();
                            String remarks = "";
                            // Create Invoice object and add to list
                            Invoice invoice = new Invoice(invoiceNumber, description, productCode, unit, quantity,
                                    price, deliveryPrice, taxRate, taxAmount, totalPrice, remarks);
                            invoices.add(invoice);
                        }else if (columns.size() == 13){

                            unit = columns.get(3).text().trim();
                            String quantity = columns.get(4).text().trim();
                            String price = columns.get(5).text().trim();
                            String deliveryPrice = columns.get(8).text().trim();
                            String taxRate = columns.get(9).text().trim();
                            String taxAmount = columns.get(10).text().trim();
                            String totalPrice = columns.get(11).text().trim();
                            String remarks = columns.get(12).text().trim();
                            // Create Invoice object and add to list
                            Invoice invoice = new Invoice(invoiceNumber, description, productCode, unit, quantity,
                                    price, deliveryPrice, taxRate, taxAmount, totalPrice, remarks);
                            invoices.add(invoice);
                        }else{
                            String quantity = columns.get(4).text().trim();
                            String price = columns.get(5).text().trim();
                            String deliveryPrice = columns.get(6).text().trim();
                            String taxRate = columns.get(7).text().trim();
                            String taxAmount = columns.get(8).text().trim();
                            String totalPrice = columns.get(9).text().trim();
                            String remarks = columns.get(10).text().trim();
                            // Create Invoice object and add to list
                            Invoice invoice = new Invoice(invoiceNumber, description, productCode, unit, quantity,
                                    price, deliveryPrice, taxRate, taxAmount, totalPrice, remarks);
                            invoices.add(invoice);
                        }

                    }
                }

                // Print the invoices
                for (Invoice invoice : invoices) {
                    System.out.println(invoice);
                }
                facturaData.setInvoices(invoices);
                return facturaData;
            } else {
                System.out.println("Table with id 'products' not found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FacturaData();
    }

    public boolean areAllCharactersNumbers(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
}
