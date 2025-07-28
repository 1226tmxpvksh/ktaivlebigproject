package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UserRegisterCommand {

    private String email;
    private String password;
    private String nickname;
}
