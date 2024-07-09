package uz.cluster.excel.lb;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import uz.cluster.dao.Document;
import uz.cluster.dao.Response;
import uz.cluster.dao.TestProduct;
import uz.cluster.excel.style.CustomXSSFRow;
import uz.cluster.excel.style.ExcelStyle;

import java.sql.Date;


@Service
@RequiredArgsConstructor
public class ReportSheet {

    CustomXSSFRow rowInTable;

    XSSFCellStyle cellStyleCenterBackgroundGreenWithBorder;
    XSSFCellStyle cellStyleCenterOnlyFontWithBorderWithBold;
    XSSFCellStyle cellStyleCenterBackgroundOrangeWithBorder;

    XSSFCell cell1;
    XSSFCell cell2;
    XSSFCell cell3;
    XSSFCell cell4;
    XSSFCell cell5;
    XSSFCell cell6;
    XSSFCell cell7;
    XSSFCell cell8;
    XSSFCell cell9;
    XSSFCell cell10;
    XSSFCell cell11;
    XSSFCell cell12;
    XSSFCell cell13;
    XSSFCell cell14;
    XSSFCell cell15;
    XSSFCell cell16;
    XSSFCell cell17;
    XSSFCell cell18;
    XSSFCell cell19;
    XSSFCell cell20;
    XSSFCell cell21;
    XSSFCell cell22;
    XSSFCell cell23;
    XSSFCell cell24;
    XSSFCell cell25;

    int counter = 0;

    public void purchaseReportSheet(XSSFWorkbook workbook, Response response,byte owner) {
        counter = 0;
        try {

            XSSFSheet sheet = workbook.createSheet(owner == 0 ? "Kiruvchi" : "Chiquvchi");

            sheet.setAutoFilter(CellRangeAddress.valueOf("C3:Z3"));
            sheet.setZoom(75);
            sheet.addIgnoredErrors(new CellRangeAddress(0, 30000, 0, 25), IgnoredErrorType.NUMBER_STORED_AS_TEXT);
            cellStyleCenterBackgroundGreenWithBorder = ExcelStyle.cellStyleCenterBackgroundGreenWithBorder(workbook);
            cellStyleCenterOnlyFontWithBorderWithBold = ExcelStyle.cellStyleCenterOnlyFontWithBorderWithBold(workbook);
            cellStyleCenterBackgroundOrangeWithBorder = ExcelStyle.cellStyleCenterBackgroundOrangeWithBorder(workbook);

            int currColumnIndex = 1;
            for (int i = 1; i <= 25; i++) {
                if (i == 1) {
                    sheet.setColumnWidth(currColumnIndex++, 256 * 13);
                }else if (i == 2 || i == 12) {
                    sheet.setColumnWidth(currColumnIndex++, 256 * 50);
                } else {
                    sheet.setColumnWidth(currColumnIndex++, 256 * 24);
                }
            }
            counter++;

            for (int i = 1; i <= 25; i++) {
                if (i == 2 || i == 4 || i == 15 || i == 23) {
                    sheet.addMergedRegion(new CellRangeAddress(counter, counter, i, i + 1));
                }else if (i == 6 || i == 9){
                    sheet.addMergedRegion(new CellRangeAddress(counter, counter, i, i + 2));
                }else if (i == 1 || i == 12 || i == 13 || i == 14 || i == 17 || i == 18 || i == 19 || i == 20 || i == 21 || i == 22 || i == 25){
                    sheet.addMergedRegion(new CellRangeAddress(counter, counter + 1, i, i));
                }
            }

            rowInTable = new CustomXSSFRow(sheet.createRow(counter), cellStyleCenterBackgroundGreenWithBorder);
            rowInTable.setHeight((short) 450);
            cell1 = rowInTable.createCell(CellType.STRING);
            cell1.setCellValue("T/R");
            cell2 = rowInTable.createCell(CellType.STRING);
            cell2.setCellValue("Sotuvchi");
            cell3 = rowInTable.createCell(CellType.STRING);
            cell4 = rowInTable.createCell(CellType.STRING);
            cell4.setCellValue("Shartnoma");
            cell5 = rowInTable.createCell(CellType.STRING);
            cell6 = rowInTable.createCell(CellType.STRING);
            cell6.setCellValue("Hisob-faktura");
            cell7 = rowInTable.createCell(CellType.STRING);
            cell8 = rowInTable.createCell(CellType.STRING);
            cell9 = rowInTable.createCell(CellType.STRING);
            cell9.setCellValue("Ishonchnoma");
            cell10 = rowInTable.createCell(CellType.STRING);
            cell11 = rowInTable.createCell(CellType.STRING);
            cell12 = rowInTable.createCell(CellType.STRING);
            cell12.setCellValue("Tovar (ish,xizmat)lar nomi");
            cell13 = rowInTable.createCell(CellType.STRING);
            cell13.setCellValue("Ident. Kod");
            cell14 = rowInTable.createCell(CellType.STRING);
            cell14.setCellValue("Shtrix kodi");
            cell15 = rowInTable.createCell(CellType.STRING);
            cell15.setCellValue("O'ram turi");
            cell16 = rowInTable.createCell(CellType.STRING);
            cell17 = rowInTable.createCell(CellType.STRING);
            cell17.setCellValue("Miqdori");
            cell18 = rowInTable.createCell(CellType.STRING);
            cell18.setCellValue("Narxi");
            cell19 = rowInTable.createCell(CellType.STRING);
            cell19.setCellValue("Yetkazib berish qiymati");
            cell20 = rowInTable.createCell(CellType.STRING);
            cell20.setCellValue("Aksiz solig'i summasi");
            cell21 = rowInTable.createCell(CellType.STRING);
            cell21.setCellValue("QQS summasi");
            cell22 = rowInTable.createCell(CellType.STRING);
            cell22.setCellValue("Yetkazib berishning QQSni ba aksiz solig'ini xisobga olgan holatda qiymati");
            cell23 = rowInTable.createCell(CellType.STRING);
            cell23.setCellValue("Imzolangan sana");
            cell24 = rowInTable.createCell(CellType.STRING);
            cell25 = rowInTable.createCell(CellType.STRING);
            cell25.setCellValue("Holati");
            counter++;
            rowInTable = new CustomXSSFRow(sheet.createRow(counter), cellStyleCenterBackgroundGreenWithBorder);
            rowInTable.setHeight((short) 450);
            cell1 = rowInTable.createCell(CellType.STRING);
            cell2 = rowInTable.createCell(CellType.STRING);
            cell2.setCellValue("Nomi");
            cell3 = rowInTable.createCell(CellType.STRING);
            cell3.setCellValue("STIR");
            cell4 = rowInTable.createCell(CellType.STRING);
            cell4.setCellValue("Raqami");
            cell5 = rowInTable.createCell(CellType.STRING);
            cell5.setCellValue("Sanasi");
            cell6 = rowInTable.createCell(CellType.STRING);
            cell6.setCellValue("Raqami");
            cell7 = rowInTable.createCell(CellType.STRING);
            cell7.setCellValue("Sanasi");
            cell8 = rowInTable.createCell(CellType.STRING);
            cell8.setCellValue("Turi");
            cell9 = rowInTable.createCell(CellType.STRING);
            cell9.setCellValue("Raqami");
            cell10 = rowInTable.createCell(CellType.STRING);
            cell10.setCellValue("Sanasi");
            cell11 = rowInTable.createCell(CellType.STRING);
            cell11.setCellValue("FIO");
            cell12 = rowInTable.createCell(CellType.STRING);
            cell13 = rowInTable.createCell(CellType.STRING);
            cell14 = rowInTable.createCell(CellType.STRING);
            cell15 = rowInTable.createCell(CellType.STRING);
            cell15.setCellValue("Raqami");
            cell16 = rowInTable.createCell(CellType.STRING);
            cell16.setCellValue("Nomi");
            cell17 = rowInTable.createCell(CellType.STRING);
            cell18 = rowInTable.createCell(CellType.STRING);
            cell19 = rowInTable.createCell(CellType.STRING);
            cell20 = rowInTable.createCell(CellType.STRING);
            cell21 = rowInTable.createCell(CellType.STRING);
            cell22 = rowInTable.createCell(CellType.STRING);
            cell23 = rowInTable.createCell(CellType.STRING);
            cell23.setCellValue("yetkazib beruvchi");
            cell24 = rowInTable.createCell(CellType.STRING);
            cell24.setCellValue("sotib oluvchi");
            cell25 = rowInTable.createCell(CellType.STRING);
            counter++;

            int count = 0;
            for (Document doc : response.getData()) {
                for (TestProduct product : doc.getProducts()) {
                    if (doc.getDocument_json().getFacturadoc() != null){
                        count++;
                        rowInTable = new CustomXSSFRow(sheet.createRow(counter), cellStyleCenterOnlyFontWithBorderWithBold);
                        CellStyle cellStyle = workbook.createCellStyle();
                        CreationHelper createHelper = workbook.getCreationHelper();
                        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd.mm.yyyy"));
                        cellStyle.setBorderBottom(BorderStyle.THIN);
                        cellStyle.setBorderTop(BorderStyle.THIN);
                        cellStyle.setBorderRight(BorderStyle.THIN);
                        cellStyle.setBorderLeft(BorderStyle.THIN);
                        cellStyle.setAlignment(HorizontalAlignment.CENTER);
                        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                        Font font = workbook.createFont();
                        font.setBold(true);
                        font.setFontName("Arial");
                        cellStyle.setFont(font);
                        cell1 = rowInTable.createCell(CellType.STRING);
                        cell1.setCellValue(count + "");
                        cell2 = rowInTable.createCell(CellType.STRING);
                        cell3 = rowInTable.createCell(CellType.STRING);
                        if (owner == 0){
                            cell2.setCellValue(doc.getDocument_json().getSeller().getName());
                            cell3.setCellValue(doc.getDocument_json().getSellertin());
                        }else{
                            cell2.setCellValue(doc.getDocument_json().getBuyer().getName());
                            cell3.setCellValue(doc.getDocument_json().getBuyertin());
                        }
                        cell4 = rowInTable.createCell(CellType.STRING);
                        cell4.setCellValue(doc.getDocument_json().getContractdoc().getContractno());

                        cell5 = rowInTable.createCell(CellType.STRING);
                        cell5.setCellStyle(cellStyle);
                        if (doc.getDocument_json().getContractdoc().getContractdate().length() < 11){
                            cell5.setCellValue(Date.valueOf(doc.getDocument_json().getContractdoc().getContractdate()));
                        }else {
                            cell5.setCellValue(Date.valueOf(doc.getDocument_json().getContractdoc().getContractdate().substring(0,10)));
                        }
                        cell6 = rowInTable.createCell(CellType.STRING);
                        cell6.setCellValue(doc.getDocument_json().getFacturadoc().getFacturano());
                        cell7 = rowInTable.createCell(CellType.STRING);
                        cell7.setCellStyle(cellStyle);
                        if (doc.getDocument_json().getFacturadoc().getFacturadate().length() < 11){
                            cell7.setCellValue(Date.valueOf(doc.getDocument_json().getFacturadoc().getFacturadate()));
                        }else {
                            cell7.setCellValue(Date.valueOf(doc.getDocument_json().getFacturadoc().getFacturadate().substring(0,10)));
                        }
                        cell8 = rowInTable.createCell(CellType.STRING);
                        switch (doc.getDocument_json().getVersion()){
                            case "1":
                                cell8.setCellValue("Стандарт");
                                break;
                            default:
                                cell8.setCellValue(doc.getDocument_json().getVersion());
                                break;
                        }
                        cell9 = rowInTable.createCell(CellType.STRING);
                        cell10 = rowInTable.createCell(CellType.STRING);
                        cell11 = rowInTable.createCell(CellType.STRING);
                        if (doc.getDocument_json().getFacturaempowermentdoc() != null){
                            cell9.setCellValue(doc.getDocument_json().getFacturaempowermentdoc().getEmpowermentno());
                            cell10.setCellValue(doc.getDocument_json().getFacturaempowermentdoc().getEmpowermentdateofissue());
                            cell11.setCellValue(doc.getDocument_json().getFacturaempowermentdoc().getAgentfio());
                        }else {
                            cell9.setCellValue("");
                            cell10.setCellValue("");
                            cell11.setCellValue("");
                        }
                        cell12 = rowInTable.createCell(CellType.STRING);
                        cell12.setCellValue(product.getName());
                        cell13 = rowInTable.createCell(CellType.STRING);
                        cell13.setCellValue(product.getCatalogcode());
                        cell14 = rowInTable.createCell(CellType.STRING);
                        cell14.setCellValue(product.getBarcode());
                        cell15 = rowInTable.createCell(CellType.NUMERIC);
                        cell15.setCellValue(product.getPackagecode());
                        cell16 = rowInTable.createCell(CellType.STRING);
                        cell16.setCellValue(product.getPackagename());
                        cell17 = rowInTable.createCell(CellType.NUMERIC);
                        cell17.setCellValue(product.getCount());
                        cell18 = rowInTable.createCell(CellType.NUMERIC);
                        cell18.setCellValue(product.getSumma());
                        cell19 = rowInTable.createCell(CellType.NUMERIC);
                        cell19.setCellValue(product.getDeliverysum());
                        cell20 = rowInTable.createCell(CellType.NUMERIC);
                        cell20.setCellValue(product.getExcisesum());
                        cell21 = rowInTable.createCell(CellType.NUMERIC);
                        cell21.setCellValue(product.getVatsum());
                        cell22 = rowInTable.createCell(CellType.NUMERIC);
                        cell22.setCellValue(product.getDeliverysumwithvat());
                        cell23 = rowInTable.createCell(CellType.STRING);
                        cell23.setCellStyle(cellStyle);
                        if (doc.getCreated().length() < 11){
                            cell23.setCellValue(Date.valueOf(doc.getCreated()));
                        }else {
                            cell23.setCellValue(Date.valueOf(doc.getCreated().substring(0,10)));
                        }
                        cell24 = rowInTable.createCell(CellType.STRING);
                        cell24.setCellStyle(cellStyle);
                        if (doc.getUpdated().length() < 11){
                            cell24.setCellValue(Date.valueOf(doc.getUpdated()));
                        }else {
                            cell24.setCellValue(Date.valueOf(doc.getUpdated().substring(0,10)));
                        }
                        cell25 = rowInTable.createCell(CellType.STRING);
                        switch (doc.getDoc_status()){
                            case 1:
                                cell25.setCellValue("Ожидает подписи партнера");
                                break;
                            case 2:
                                cell25.setCellValue("Ожидает подписи партнёра");
                                break;
                            case 3:
                                cell25.setCellValue("Подтверждён");
                                break;
                            case 4:
                                cell25.setCellValue("Отказано");
                                break;
                        }
                        counter++;
                    }
                }
            }


            sheet.addMergedRegion(new CellRangeAddress(counter, counter, 1, 15));
            rowInTable = new CustomXSSFRow(sheet.createRow(counter), cellStyleCenterBackgroundGreenWithBorder);
            rowInTable.setHeight((short) 450);
            cell1 = rowInTable.createCell(CellType.STRING);
            cell1.setCellValue("Jami");
            cell2 = rowInTable.createCell(CellType.STRING);
            cell3 = rowInTable.createCell(CellType.STRING);
            cell4 = rowInTable.createCell(CellType.STRING);
            cell5 = rowInTable.createCell(CellType.STRING);
            cell6 = rowInTable.createCell(CellType.STRING);
            cell7 = rowInTable.createCell(CellType.STRING);
            cell8 = rowInTable.createCell(CellType.STRING);
            cell9 = rowInTable.createCell(CellType.STRING);
            cell10 = rowInTable.createCell(CellType.STRING);
            cell11 = rowInTable.createCell(CellType.STRING);
            cell12 = rowInTable.createCell(CellType.STRING);
            cell13 = rowInTable.createCell(CellType.STRING);
            cell14 = rowInTable.createCell(CellType.STRING);
            cell15 = rowInTable.createCell(CellType.STRING);
            cell16 = rowInTable.createCell(CellType.STRING);
            cell17 = rowInTable.createCell(CellType.FORMULA);
            cell17.setCellFormula("SUM(R4:R" + (3 + count) + ")");
            cell18 = rowInTable.createCell(CellType.STRING);
            cell19 = rowInTable.createCell(CellType.FORMULA);
            cell19.setCellFormula("SUM(T4:T" + (3 + count) + ")");
            cell20 = rowInTable.createCell(CellType.STRING);
            cell21 = rowInTable.createCell(CellType.FORMULA);
            cell21.setCellFormula("SUM(V4:V" + (3 + count) + ")");
            cell22 = rowInTable.createCell(CellType.FORMULA);
            cell22.setCellFormula("SUM(W4:W" + (3 + count) + ")");
            cell23 = rowInTable.createCell(CellType.STRING);
            cell24 = rowInTable.createCell(CellType.STRING);
            cell25 = rowInTable.createCell(CellType.STRING);
            counter++;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
