package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.*;
import ktaivlebigproject.domain.*;
import ktaivlebigproject.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ReportSaved extends AbstractEvent {

    private Long reportId;
    private Date savedAt;

    public ReportSaved(Report aggregate) {
        super(aggregate);
    }

    public ReportSaved() {
        super();
    }
}
//>>> DDD / Domain Event
