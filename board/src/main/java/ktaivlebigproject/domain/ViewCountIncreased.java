package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.*;
import ktaivlebigproject.domain.*;
import ktaivlebigproject.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ViewCountIncreased extends AbstractEvent {

    private Long boardId;
    private Integer viewCount;
    private Date updatedAt;

    public ViewCountIncreased(Board aggregate) {
        super(aggregate);
    }

    public ViewCountIncreased() {
        super();
    }
}
//>>> DDD / Domain Event
