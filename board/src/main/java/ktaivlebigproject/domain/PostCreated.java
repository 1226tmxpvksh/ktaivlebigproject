package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.*;
import ktaivlebigproject.domain.*;
import ktaivlebigproject.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PostCreated extends AbstractEvent {

    private Long boardId;
    private String title;
    private String content;
    private Long authorId;
    private String type;
    private File attachments;
    private Date createdAt;

    public PostCreated(Board aggregate) {
        super(aggregate);
    }

    public PostCreated() {
        super();
    }
}
//>>> DDD / Domain Event
