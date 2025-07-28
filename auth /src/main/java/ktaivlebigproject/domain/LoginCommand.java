package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class LoginCommand {

    private Long authId;
    private Long userId;
}
