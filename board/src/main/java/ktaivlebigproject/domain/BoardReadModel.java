package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "BoardReadModel_table")
@Data
public class BoardReadModel {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long boardId;

    private String title;
    private String content;
    private Long authorId;
    private Integer viewCount;
    private String type;
    private String attachments;
    private Date createdAt;
    private Date updatedAt;
}
