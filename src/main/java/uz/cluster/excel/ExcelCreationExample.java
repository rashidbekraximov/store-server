package uz.cluster.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import uz.cluster.dao.FacturaData;
import uz.cluster.dao.Invoice;
import uz.cluster.services.SoliqService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service

public class ExcelCreationExample {

    public Workbook createExcelWorkbook() {
        List<FacturaData> facturaData = SoliqService.map.get(1);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Invoices");

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] columns = {"ID","Holati","Faktura No","Faktura Sana","Shartnoma No:","Shartnoma sana","Sotuvchi STIR","Sotuvchi nomi",
                "Sotib Oluvchi STIR","Sotib oluvchi nomi", "Description", "Product Code", "Unit", "Quantity", "Price",
                "Delivery Price", "Tax Rate", "Tax Amount", "Total Price", "Remarks"};

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Font headerFont = workbook.createFont();
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerCellStyle.setFont(headerFont);

        // Create headers
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create rows with data
        int rowNum = 1;
        int count = 1;
        for (FacturaData facturaData1 : facturaData){
            if (facturaData1 != null && facturaData1.getInvoices() != null){
                for (int i = 1; i < facturaData1.getInvoices().size(); i++) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(count);
                    row.createCell(1).setCellValue(facturaData1.getStates() != null ? facturaData1.getStates().get(facturaData1.getStates().size() - 1).getStateName() : "");
                    row.createCell(2).setCellValue(facturaData1.getFacturaNo());
                    row.createCell(3).setCellValue(facturaData1.getFacturaDate());
                    row.createCell(4).setCellValue(facturaData1.getContractNo());
                    row.createCell(5).setCellValue(facturaData1.getContractDate());
                    row.createCell(6).setCellValue(facturaData1.getSellerTin());
                    row.createCell(7).setCellValue(facturaData1.getSellerName());
                    row.createCell(8).setCellValue(facturaData1.getBuyerTin());
                    row.createCell(9).setCellValue(facturaData1.getBuyerName());
                    row.createCell(10).setCellValue(facturaData1.getInvoices().get(i).getDescription());
                    row.createCell(11).setCellValue(facturaData1.getInvoices().get(i).getProductCode());
                    row.createCell(12).setCellValue(facturaData1.getInvoices().get(i).getUnit());
                    row.createCell(13).setCellValue(facturaData1.getInvoices().get(i).getQuantity());
                    row.createCell(14).setCellValue(facturaData1.getInvoices().get(i).getPrice());
                    row.createCell(15).setCellValue(facturaData1.getInvoices().get(i).getDeliveryPrice());
                    row.createCell(16).setCellValue(facturaData1.getInvoices().get(i).getTaxRate());
                    row.createCell(17).setCellValue(facturaData1.getInvoices().get(i).getTaxAmount());
                    row.createCell(18).setCellValue(facturaData1.getInvoices().get(i).getTotalPrice());
                    row.createCell(19).setCellValue(facturaData1.getInvoices().get(i).getRemarks());
                    count++;
                }
            }
        }

        // Auto-size columns
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        return workbook;
    }

}
