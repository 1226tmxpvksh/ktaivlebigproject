package ktaivlebigproject.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import ktaivlebigproject.config.kafka.KafkaProcessor;
import ktaivlebigproject.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    BoardRepository boardRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PostDetailed'"
    )
    public void wheneverPostDetailed_ViewCountPolicy(
        @Payload PostDetailed postDetailed
    ) {
        PostDetailed event = postDetailed;
        System.out.println(
            "\n\n##### listener ViewCountPolicy : " + postDetailed + "\n\n"
        );

        // Sample Logic //
        Board.viewCountPolicy(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReportSaved'"
    )
    public void wheneverReportSaved_ReportSavePolicy(
        @Payload ReportSaved reportSaved
    ) {
        ReportSaved event = reportSaved;
        System.out.println(
            "\n\n##### listener ReportSavePolicy : " + reportSaved + "\n\n"
        );

        // Sample Logic //
        Board.reportSavePolicy(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
