package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class ReportGenerateCommand {

    private Long reportId;
    private String title;
    private String content;
    private Long authorId;
    private Date createdAt;
}
