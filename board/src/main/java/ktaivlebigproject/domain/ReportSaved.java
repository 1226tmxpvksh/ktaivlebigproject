package ktaivlebigproject.domain;

import java.util.*;
import ktaivlebigproject.domain.*;
import ktaivlebigproject.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class ReportSaved extends AbstractEvent {

    private Long reportId;
    private Date savedAt;
}
