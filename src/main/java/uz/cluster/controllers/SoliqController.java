package uz.cluster.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.cluster.dao.RequestDao;
import uz.cluster.excel.ExcelCreationExample;
import uz.cluster.services.SoliqService;

import org.springframework.core.io.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class SoliqController {

    private final SoliqService soliqService;

    private final ExcelCreationExample excelCreationExample;

    @GetMapping("/")
    public String add(@ModelAttribute("soliq") final RequestDao soliq) {
        return "list";
    }

    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute RequestDao myForm, Model model) {
        // Process the form data here
        model.addAttribute("myForm", myForm);
        try {
            soliqService.loadFactura(myForm);
            return "success";
        }catch (Exception e){
            return "error";
        }
    }

    @PostMapping("/downloadExcel")
    public ResponseEntity<Resource> downloadExcel() {
        try {
            // Create a workbook and populate it with data
            Workbook workbook = excelCreationExample.createExcelWorkbook();

            // Write workbook content to ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            // Close workbook
            workbook.close();

            // Convert ByteArrayOutputStream to a ByteArrayResource
            ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

            // Set appropriate headers for the Excel file download
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "invoices.xlsx");

            // Return ResponseEntity with ByteArrayResource
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(resource.contentLength())
                    .body(resource);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception (e.g., log error)
            return ResponseEntity.badRequest().body(null); // Or handle error response accordingly
        }
    }
}
