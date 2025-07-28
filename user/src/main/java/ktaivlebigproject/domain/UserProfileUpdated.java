package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.*;
import ktaivlebigproject.domain.*;
import ktaivlebigproject.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class UserProfileUpdated extends AbstractEvent {

    private Long userId;
    private String email;
    private String password;
    private String nickname;

    public UserProfileUpdated(User aggregate) {
        super(aggregate);
    }

    public UserProfileUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
