package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.*;
import ktaivlebigproject.domain.*;
import ktaivlebigproject.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class TokenUpdated extends AbstractEvent {

    private Long authId;
    private String updateExpiredAt;
    private String updateToken;

    public TokenUpdated(Auth aggregate) {
        super(aggregate);
    }

    public TokenUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
