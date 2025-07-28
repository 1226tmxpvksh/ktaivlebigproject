package ktaivlebigproject.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import ktaivlebigproject.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/reports")
@Transactional
public class ReportController {

    @Autowired
    ReportRepository reportRepository;

    @RequestMapping(
        value = "/reports/{id}/reportdataupload",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Report reportDataUpload(
        @PathVariable(value = "id") Long id,
        @RequestBody ReportDataUploadCommand reportDataUploadCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /report/reportDataUpload  called #####");
        Optional<Report> optionalReport = reportRepository.findById(id);

        optionalReport.orElseThrow(() -> new Exception("No Entity Found"));
        Report report = optionalReport.get();
        report.reportDataUpload(reportDataUploadCommand);

        reportRepository.save(report);
        return report;
    }

    @RequestMapping(
        value = "/reports/{id}/reportgenerate",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Report reportGenerate(
        @PathVariable(value = "id") Long id,
        @RequestBody ReportGenerateCommand reportGenerateCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /report/reportGenerate  called #####");
        Optional<Report> optionalReport = reportRepository.findById(id);

        optionalReport.orElseThrow(() -> new Exception("No Entity Found"));
        Report report = optionalReport.get();
        report.reportGenerate(reportGenerateCommand);

        reportRepository.save(report);
        return report;
    }

    @RequestMapping(
        value = "/reports/{id}/reportsave",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Report reportSave(
        @PathVariable(value = "id") Long id,
        @RequestBody ReportSaveCommand reportSaveCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /report/reportSave  called #####");
        Optional<Report> optionalReport = reportRepository.findById(id);

        optionalReport.orElseThrow(() -> new Exception("No Entity Found"));
        Report report = optionalReport.get();
        report.reportSave(reportSaveCommand);

        reportRepository.save(report);
        return report;
    }
}
//>>> Clean Arch / Inbound Adaptor
