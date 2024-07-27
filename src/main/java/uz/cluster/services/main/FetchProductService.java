package uz.cluster.services.main;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import uz.cluster.entity.Good;
import uz.cluster.entity.ParentData;
import uz.cluster.entity.SubOpt;
import uz.cluster.entity.Tab;
import uz.cluster.entity.main.Product;
import uz.cluster.entity.main.SubTab;
import uz.cluster.enums.Category;
import uz.cluster.services.TranslatorService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FetchProductService {

    private final TranslatorService translatorService;

    public String loadMainData(int i) {
        try {
            // Define the URL
            String urlString = "https://mobile.yangkeduo.com/proxy/api/api/alexa/cells/hub/v3?pdduid=4548925868544&platform=H5&page_sn=10002&page_id=index_list.html&engine_version=3.0&offset=" + i + "" +
                    "&count=100&" +
                    "list_id=qwko6t0bxn" +
                    "&" +
                    "anti_content=0asWfqnYNiHgygv2p2fjOqB55uo2w68f4Jvk6WYMxp7eBhjQ3PMm7xfcrFOF8_md6DdJ7wUyoTVgD_7gFXwZwLtz2BOTm9pwgOw226yyPIP208PKUuo5ZeO4XvfiTRTVCuk6MRRUrsD9G9RCnzWcJEzDC0lAhgl0zmV1vyDEtgC76kKNfQRko-tVLki8m16VLC8WE5j_Ksmi0xuPIr0l-aSrcK7bgc5yjD66bFomQQaspg3uWbq2Ab_oiuKu9Q7b03x5zLgjLiwenoWVQWP0EOdj-e1vtlJblJzjca_CGfLFGAnCB_mZzRP0WPWN_rx3ZKsUojKD8xTHaRocuHB2hvjyO75ek3qlHnL6BhtKbfqtzzfEcTA4dJHQBlV-4GU1fiU7kDElTpQudLCxqhbWsk8-yxQlkFJznRfhf-Pqi5vdTvzq48GnXjpy0ddW--B5_PhtEPHY9RyHdi6WL1cqrm2bjLCUpExVhU5lnrHi13O3CXOqrSPghiKcvCDJiJ3Z4Ibvo5aXI86i2MODuV1l-5MeaGSFSHkkXGU0XuL8BLBnnx4nYQcsUIkEOQT-9F-6Ows1RUqlsxh15Xbl2bDrMPyH4HXVffTivLMGjJTObIdbiylcS7eXd_s4oUCF8I6kB_SfEeydOxPmstExhzuzPqzY1EXNLstuUGXBmsXmJ2IrHNSsbNPKh0jN_G3H_VPqRjil0Eknb94tht07oMO-2lxy1oqRRim6-O7SOyWRHj3lK7BX8gz1J2RIGRK0VwqIvZMtP_5bD5hwotUX3gN0-dLwLPKzKxs1nxeGUV_UTwweg_tYGa2rq8CHADpJ9QSWHTYR-up";

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("GET");

            // Set request headers
            Map<String, String> headers = new HashMap<>();
            headers.put("Cookie", "api_uid=CigYCGaJka2KlwBXn5ieAg==; _nano_fp=XpmxX0PbXpmjX0doXT_nV5ZvDvsEMydYN1pZjAKf; webp=1; jrpl=zViRwgQCsnUpnt9zzpGUrPS9lGku5XLO; njrpl=zViRwgQCsnUpnt9zzpGUrPS9lGku5XLO; dilx=O4jEN1EmSMqkKIgsWNlSL; PDDAccessToken=6QHXOCLPQC442FMV7JZVPG3QKLIA5ZWAZE243GCI46HR7576O7QQ122006a; pdd_user_id=4548925868544; pdd_user_uin=NHBJAIRGBKAZMDMLJN5LBYXVL4_GEXDA; pdd_vds=gaLLNOimiiIEtanNioaLIyOitoGIaLNaLNPQaGamyGGNPPIGEImQoaNaOPOb");
            headers.put("Priority", "u=1, i");
            headers.put("Referer", "https://mobile.yangkeduo.com/?page_id=10002_1721199784320_6vs7wdqpk9&bsch_is_search_mall=&bsch_show_active_page=");
            headers.put("Sec-Ch-Ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"");
            headers.put("Sec-Ch-Ua-Mobile", "?0");
            headers.put("Sec-Ch-Ua-Platform", "\"Windows\"");
            headers.put("Sec-Fetch-Dest", "empty");
            headers.put("Sec-Fetch-Mode", "cors");
            headers.put("Sec-Fetch-Site", "same-origin");
            headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");
            headers.put("Verifyauthtoken", "ZAv9-bk9tGyG3bcCao7PlQaabea93852789aebb");

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            // Close the connections
            in.close();
            connection.disconnect();

            // Convert response to JSON
            JSONObject jsonResponse = new JSONObject(content.toString());
            System.out.println(jsonResponse.toString(4));
            return jsonResponse.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String loadMainCategoryData(int i) {
        try {
            // Define the URL
            String urlString = "https://mobile.yangkeduo.com/proxy/api/api/caterham/query/fenlei_gyl_group?pdduid=4548925868544&opt_type=1&count=40&support_types=0_4_5_6_7_9&opt_name=&platform=H5&page_id=index.html%3Fdy_sub_page%3Dcategory&page_sn=10002&opt_id=" + TabService.globalReferences.get(Category.MANS).getOptId() + "&offset=" + i + "&list_id=" + TabService.globalReferences.get(Category.MANS).getListId().replace(TabService.globalReferences.get(Category.MANS).getOptId() + "","") + "&anti_content=0asWtqlFBifsJsuaPR0JiwV491CGM7rVeZ6mSWJhicQ68-Xl3cQ_uMmT_q0JurcJQJhJ6yJ3iTK1krsV5fFpSIwUf5w4X6EGGMf44Fn4ksfLLsVG5p4tl1_mJhtrNTSR0Ja0wlC29B9hauWK_Mb8wpWPAHkvLrkm94rAvhQmaJVEcjkxr-6nBCJ9jjoJgikvG_nMLY-uj5d3gmCxmHoLs6j0-Hrsuy3I0_QYvjyja-MDJr4zpCeu6Y2jizljxaobGAI0_OiZ6vERft7b4vE8skXulsBcc9ARlwBl5ceAb6JLgmNmQn7X2lVKbfj9Hx2409g9zLZ6J4P940_zEU2gkLUqJg2gzm_XPxOeaDhhbPiVQyMbXD3J9qQcQT7VtPQ3a8YJWolLV-B-dy-HGvquHGVWqwiLYjhbtwOGOFV_kfFXztRMIt3JMiE9dBdb9O1G8jQNnytvyIX55MDnO3zO5x7jW2CDDmjKK5-o6XtKHyO4lpzE07A3mJYc_sS3woaP2zVpd01eJ5WmHnTR457KdV0xaq7QbEZajeJX5M-Egyi1CCHybIIe50HfeM7vvzXLF7d5fywBQFZQb5GbtVQeK1U7KFhajg-vdF_zCtWjhCJRg55t_KkLWNrpIZG_5m4O1d_7b-wQIASkpiBuxrpgMfeURQuNMRFJ4fe8pM2PsxvQ46wpzyesmEdrtZjyUGrRyotWfaqx5DLKhTS0roYqUL6kwfNYwDb9nOaNaAgp5KW4hld6vxPjWftw7nzhpOzg4r4RJg8pCXiMWzUGzYOLdQ62njfifwcJlnrQMdB1VWOeIwJs1e6bHIVd3DFbMwOEHpLHExNegKmy5wup7_5hd7a9ANZi9CZ5w6NcgTLza0";

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("GET");

            // Set request headers
            Map<String, String> headers = new HashMap<>();
            headers.put("Cookie", "api_uid=CigYCGaJka2KlwBXn5ieAg==; _nano_fp=XpmxX0PbXpmjX0doXT_nV5ZvDvsEMydYN1pZjAKf; webp=1; jrpl=zViRwgQCsnUpnt9zzpGUrPS9lGku5XLO; njrpl=zViRwgQCsnUpnt9zzpGUrPS9lGku5XLO; dilx=O4jEN1EmSMqkKIgsWNlSL; PDDAccessToken=6QHXOCLPQC442FMV7JZVPG3QKLIA5ZWAZE243GCI46HR7576O7QQ122006a; pdd_user_id=4548925868544; pdd_user_uin=NHBJAIRGBKAZMDMLJN5LBYXVL4_GEXDA; pdd_vds=gaLLNOimiiIEtanNioaLIyOitoGIaLNaLNPQaGamyGGNPPIGEImQoaNaOPOb");
            headers.put("Priority", "u=1, i");
            headers.put("Referer", "https://mobile.yangkeduo.com/?page_id=10002_1721299710008_ku9ugvhsj9&bsch_is_search_mall=&bsch_show_active_page=");
            headers.put("Sec-Ch-Ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"");
            headers.put("Sec-Ch-Ua-Mobile", "?0");
            headers.put("Sec-Ch-Ua-Platform", "\"Windows\"");
            headers.put("Sec-Fetch-Dest", "empty");
            headers.put("Sec-Fetch-Mode", "cors");
            headers.put("Sec-Fetch-Site", "same-origin");
            headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");
            headers.put("Verifyauthtoken", "ZAv9-bk9tGyG3bcCao7PlQaabea93852789aebb");

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            // Close the connections
            in.close();
            connection.disconnect();

            // Convert response to JSON
            JSONObject jsonResponse = new JSONObject(content.toString());
            System.out.println(jsonResponse.toString(4));
            return jsonResponse.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> fetchAndSaveGoods(int num) {
        String jsonResponse = loadMainData(num);
        if (jsonResponse == null) {
            return null;
        }

        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONObject jsonData = jsonObject.getJSONObject("data");

        JSONArray goodsListArray = jsonData.getJSONArray("goods_list");
        List<Product> goodsLists = new ArrayList<>();
        for (int i = 0; i < goodsListArray.length(); i++) {
            JSONObject goodsListObject = goodsListArray.getJSONObject(i);
            JSONObject product = goodsListObject.getJSONObject("data");
            Product productSave = new Product();
            productSave.setGoodsName(translatorService.translator(product.getString("goods_name")));
            productSave.setHdThumbUrl(product.getString("hd_thumb_url"));
            productSave.setHdUrl(product.getString("hd_url"));
            productSave.setThumbUrl(product.getString("thumb_url"));
            productSave.setPrice(product.getInt("price"));
            productSave.setQuantity(product.getInt("quantity"));
            productSave.setGoodsId(product.getLong("goods_id"));
            goodsLists.add(productSave);
        }
        return goodsLists;
    }

    public List<Product> fetchAndSaveMainGoods(int num) {
        String jsonResponse = loadMainCategoryData(num);
        if (jsonResponse == null) {
            return null;
        }

        JSONObject jsonObject = new JSONObject(jsonResponse);

        JSONArray goodsListArray = jsonObject.getJSONArray("list");
        List<Product> goodsLists = new ArrayList<>();
        for (int i = 0; i < goodsListArray.length(); i++) {
            JSONObject product = goodsListArray.getJSONObject(i);
            Product productSave = new Product();
            productSave.setGoodsName(translatorService.translator(product.getString("goods_name")));
            productSave.setHdThumbUrl(product.getString("hd_thumb_url"));
            productSave.setHdUrl(product.getString("hd_url"));
            productSave.setThumbUrl(product.getString("thumb_url"));
            productSave.setPrice(product.getInt("price"));
            productSave.setQuantity(product.getInt("quantity"));
            productSave.setGoodsId(product.getLong("goods_id"));
            productSave.setCategory(Category.MANS);
            goodsLists.add(productSave);
        }
        return goodsLists;
    }
}
