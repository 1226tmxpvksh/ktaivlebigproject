package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.*;
import ktaivlebigproject.domain.*;
import ktaivlebigproject.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class LoggedIn extends AbstractEvent {

    private Long authId;
    private String updateExpiredAt;
    private Long userId;
    private String updateToken;

    public LoggedIn(Auth aggregate) {
        super(aggregate);
    }

    public LoggedIn() {
        super();
    }
}
//>>> DDD / Domain Event
