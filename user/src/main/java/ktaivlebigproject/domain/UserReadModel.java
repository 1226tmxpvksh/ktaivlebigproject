package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "UserReadModel_table")
@Data
public class UserReadModel {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;

    private String email;
    private String nickname;
}
