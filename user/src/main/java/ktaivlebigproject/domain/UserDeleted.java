package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.*;
import ktaivlebigproject.domain.*;
import ktaivlebigproject.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class UserDeleted extends AbstractEvent {

    private Long userId;

    public UserDeleted(User aggregate) {
        super(aggregate);
    }

    public UserDeleted() {
        super();
    }
}
//>>> DDD / Domain Event
