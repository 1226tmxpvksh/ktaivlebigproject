package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "AuthReadModel_table")
@Data
public class AuthReadModel {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long authId;

    private Long userId;
    private String updateToken;
    private String updateExpiredAt;
}
