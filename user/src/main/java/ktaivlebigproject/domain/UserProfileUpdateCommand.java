package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UserProfileUpdateCommand {

    private Long userId;
    private String email;
    private String password;
    private String nickname;
}
