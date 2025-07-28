package ktaivlebigproject.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import ktaivlebigproject.UserApplication;
import lombok.Data;

@Entity
@Table(name = "User_table")
@Data
//<<< DDD / Aggregate Root
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String email;

    private String password;

    private String nickname;

    public static UserRepository repository() {
        UserRepository userRepository = UserApplication.applicationContext.getBean(
            UserRepository.class
        );
        return userRepository;
    }

    //<<< Clean Arch / Port Method
    public void userRegister(UserRegisterCommand userRegisterCommand) {
        //implement business logic here:

        UserRegistered userRegistered = new UserRegistered(this);
        userRegistered.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void userDelete(UserDeleteCommand userDeleteCommand) {
        //implement business logic here:

        UserDeleted userDeleted = new UserDeleted(this);
        userDeleted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void userProfileUpdate(
        UserProfileUpdateCommand userProfileUpdateCommand
    ) {
        //implement business logic here:

        UserProfileUpdated userProfileUpdated = new UserProfileUpdated(this);
        userProfileUpdated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void getUserInfo(GetUserInfoCommand getUserInfoCommand) {
        //implement business logic here:

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
