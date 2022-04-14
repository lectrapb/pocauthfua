package co.com.bancolombia.security.api.view;

import co.com.bancolombia.security.model.logs.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class ReportXlsxView {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Log> logs;

    public ReportXlsxView(List<Log> logs) {
        this.logs = logs;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("LOGS-POC");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "log_id", style);
        createCell(row, 1, "log_message_id", style);
        createCell(row, 2, "log_app_id", style);
        createCell(row, 3, "log_url", style);
        createCell(row, 4, "log_status", style);
        createCell(row, 5, "log_data", style);
        createCell(row, 6, "log_date", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Log log : logs) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, log.getId().toString(), style);
            createCell(row, columnCount++, log.getMessageId(), style);
            createCell(row, columnCount++, log.getAppId(), style);
            createCell(row, columnCount++, log.getAction(), style);
            createCell(row, columnCount++, log.getStatus(), style);
            createCell(row, columnCount++, log.getData(), style);
            createCell(row, columnCount++, log.getCreate().format(formatter), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }




}
