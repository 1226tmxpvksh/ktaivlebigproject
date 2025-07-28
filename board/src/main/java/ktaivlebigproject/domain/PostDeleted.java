package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.*;
import ktaivlebigproject.domain.*;
import ktaivlebigproject.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PostDeleted extends AbstractEvent {

    private Long boardId;

    public PostDeleted(Board aggregate) {
        super(aggregate);
    }

    public PostDeleted() {
        super();
    }
}
//>>> DDD / Domain Event
