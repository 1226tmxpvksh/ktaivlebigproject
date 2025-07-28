package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.*;
import ktaivlebigproject.domain.*;
import ktaivlebigproject.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PostDetailed extends AbstractEvent {

    private Long boardId;
    private String title;
    private String content;
    private Integer viewCount;

    public PostDetailed(Board aggregate) {
        super(aggregate);
    }

    public PostDetailed() {
        super();
    }
}
//>>> DDD / Domain Event
