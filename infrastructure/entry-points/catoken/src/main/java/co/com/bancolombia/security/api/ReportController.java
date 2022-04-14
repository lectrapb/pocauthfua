package co.com.bancolombia.security.api;


import co.com.bancolombia.security.api.view.ReportXlsxView;
import co.com.bancolombia.security.model.logs.Log;
import co.com.bancolombia.security.model.util.PathConstant;
import co.com.bancolombia.security.usecase.logs.LogsUseCase;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@Api(value="ReportController", description="Servicio generacion reportes")
public class ReportController {

    private LogsUseCase logsUseCase;

    @GetMapping(PathConstant.POST_REPORT_LOGS)
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=poc_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Log> listLogs = logsUseCase.findAll();

        ReportXlsxView excelExporter = new ReportXlsxView(listLogs);

        excelExporter.export(response);
    }
}
