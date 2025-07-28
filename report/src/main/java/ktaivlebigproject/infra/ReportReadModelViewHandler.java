package ktaivlebigproject.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import ktaivlebigproject.config.kafka.KafkaProcessor;
import ktaivlebigproject.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ReportReadModelViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private ReportReadModelRepository reportReadModelRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReportDataUploaded_then_CREATE_1(
        @Payload ReportDataUploaded reportDataUploaded
    ) {
        try {
            if (!reportDataUploaded.validate()) return;

            // view 객체 생성
            ReportReadModel reportReadModel = new ReportReadModel();
            // view 객체에 이벤트의 Value 를 set 함
            reportReadModel.setReportId(reportDataUploaded.getReportId());
            // view 레파지 토리에 save
            reportReadModelRepository.save(reportReadModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReportSaved_then_UPDATE_1(
        @Payload ReportSaved reportSaved
    ) {
        try {
            if (!reportSaved.validate()) return;
            // view 객체 조회
            Optional<ReportReadModel> reportReadModelOptional = reportReadModelRepository.findByReportId(
                reportSaved.getReportId()
            );

            if (reportReadModelOptional.isPresent()) {
                ReportReadModel reportReadModel = reportReadModelOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                reportReadModel.setSavedAt(reportSaved.getSavedAt());
                // view 레파지 토리에 save
                reportReadModelRepository.save(reportReadModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
