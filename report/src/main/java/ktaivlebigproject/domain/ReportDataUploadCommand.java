package ktaivlebigproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class ReportDataUploadCommand {

    private Long reportId;
    private Long authorId;
    private File uploadedData;
}
