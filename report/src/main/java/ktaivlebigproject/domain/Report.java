package ktaivlebigproject.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import ktaivlebigproject.ReportApplication;
import lombok.Data;

@Entity
@Table(name = "Report_table")
@Data
//<<< DDD / Aggregate Root
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reportId;

    private String title;

    private String content;

    private Long authorId;

    @Embedded
    private File uploadedData;

    private Date createdAt;

    private Date savedAt;

    public static ReportRepository repository() {
        ReportRepository reportRepository = ReportApplication.applicationContext.getBean(
            ReportRepository.class
        );
        return reportRepository;
    }

    //<<< Clean Arch / Port Method
    public void reportDataUpload(
        ReportDataUploadCommand reportDataUploadCommand
    ) {
        //implement business logic here:

        ReportDataUploaded reportDataUploaded = new ReportDataUploaded(this);
        reportDataUploaded.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void reportGenerate(ReportGenerateCommand reportGenerateCommand) {
        //implement business logic here:

        ReportGenerated reportGenerated = new ReportGenerated(this);
        reportGenerated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void reportSave(ReportSaveCommand reportSaveCommand) {
        //implement business logic here:

        ReportSaved reportSaved = new ReportSaved(this);
        reportSaved.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
