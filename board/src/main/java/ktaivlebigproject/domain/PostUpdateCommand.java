package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class PostUpdateCommand {

    private Long boardId;
    private String title;
    private String content;
    private String type;
    private File attachments;
    private Date updatedAt;
}
