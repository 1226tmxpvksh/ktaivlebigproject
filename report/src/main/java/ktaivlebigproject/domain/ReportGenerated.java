package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.*;
import ktaivlebigproject.domain.*;
import ktaivlebigproject.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ReportGenerated extends AbstractEvent {

    private Long reportId;
    private String title;
    private String content;
    private Long authorId;
    private Date createdAt;

    public ReportGenerated(Report aggregate) {
        super(aggregate);
    }

    public ReportGenerated() {
        super();
    }
}
//>>> DDD / Domain Event
