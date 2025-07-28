package ktaivlebigproject.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import ktaivlebigproject.AuthApplication;
import lombok.Data;

@Entity
@Table(name = "Auth_table")
@Data
//<<< DDD / Aggregate Root
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long authId;

    private Long userId;

    private String updateToken;

    private String updateExpiredAt;

    public static AuthRepository repository() {
        AuthRepository authRepository = AuthApplication.applicationContext.getBean(
            AuthRepository.class
        );
        return authRepository;
    }

    //<<< Clean Arch / Port Method
    public void login(LoginCommand loginCommand) {
        //implement business logic here:

        LoggedIn loggedIn = new LoggedIn(this);
        loggedIn.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void logout(LogoutCommand logoutCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void tokenReissue(TokenReissueCommand tokenReissueCommand) {
        //implement business logic here:

        TokenUpdated tokenUpdated = new TokenUpdated(this);
        tokenUpdated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void tokenVerification(
        TokenVerificationCommand tokenVerificationCommand
    ) {
        //implement business logic here:

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
