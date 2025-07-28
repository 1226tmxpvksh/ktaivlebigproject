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
public class AuthReadModelViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private AuthReadModelRepository authReadModelRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenLoggedIn_then_CREATE_1(@Payload LoggedIn loggedIn) {
        try {
            if (!loggedIn.validate()) return;

            // view 객체 생성
            AuthReadModel authReadModel = new AuthReadModel();
            // view 객체에 이벤트의 Value 를 set 함
            authReadModel.setAuthId(loggedIn.getAuthId());
            authReadModel.setUserId(loggedIn.getUserId());
            authReadModel.setUpdateToken(loggedIn.getUpdateToken());
            authReadModel.setUpdateExpiredAt(loggedIn.getUpdateExpiredAt());
            // view 레파지 토리에 save
            authReadModelRepository.save(authReadModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenTokenUpdated_then_UPDATE_1(
        @Payload TokenUpdated tokenUpdated
    ) {
        try {
            if (!tokenUpdated.validate()) return;
            // view 객체 조회

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
