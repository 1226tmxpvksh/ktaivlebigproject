package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class TokenVerificationCommand {

    private String updateExpiredAt;
}
