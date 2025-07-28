package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "ReportReadModel_table")
@Data
public class ReportReadModel {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long reportId;

    private String title;
    private String content;
    private Long authorId;
    private Date uploadedDate;
    private Date savedAt;
}
