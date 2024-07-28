package uz.cluster.excel;

import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import uz.cluster.dao.Response;
import uz.cluster.excel.lb.ReportSheet;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class Driver {

    private final ReportSheet reportSheet;

    public void excelTabelSheet(HttpServletResponse response, Response res,byte owner) throws IOException {

        String headerKey = "Content-Disposition";

        String fileName = "Tabel.xlsx";

        response.setContentType("application/octet-stream");
        response.setHeader(headerKey, "attachment; filename=" + java.net.URLEncoder.encode(fileName.replaceAll(" ", "_").toLowerCase(), StandardCharsets.UTF_8));

        XSSFWorkbook workbook = new XSSFWorkbook();

        System.out.println("Tabel starting");
        reportSheet.purchaseReportSheet(workbook,res,owner);

        System.out.println("After excel writing: " + LocalTime.now());
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        System.out.println("Workbook write time: " + LocalTime.now());
        workbook.close();
        System.out.println("Workbook close:      " + LocalTime.now());
        response.getOutputStream().flush();
        response.getOutputStream().close();
        outputStream.flush();
        outputStream.close();
        System.out.println("Output close:        " + LocalTime.now());
    }

}
