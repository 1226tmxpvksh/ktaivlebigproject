package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.*;
import ktaivlebigproject.domain.*;
import ktaivlebigproject.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ReportDataUploaded extends AbstractEvent {

    private Long reportId;
    private String title;
    private String content;
    private Long authorId;
    private File uploadedData;
    private Date savedAt;

    public ReportDataUploaded(Report aggregate) {
        super(aggregate);
    }

    public ReportDataUploaded() {
        super();
    }
}
//>>> DDD / Domain Event
