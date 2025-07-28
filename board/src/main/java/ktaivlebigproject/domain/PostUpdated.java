package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.*;
import ktaivlebigproject.domain.*;
import ktaivlebigproject.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PostUpdated extends AbstractEvent {

    private Long boardId;
    private String title;
    private String content;
    private String type;
    private File attachments;
    private Date updatedAt;

    public PostUpdated(Board aggregate) {
        super(aggregate);
    }

    public PostUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
