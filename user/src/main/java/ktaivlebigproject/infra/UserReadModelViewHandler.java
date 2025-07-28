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
public class UserReadModelViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private UserReadModelRepository userReadModelRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenUserRegistered_then_CREATE_1(
        @Payload UserRegistered userRegistered
    ) {
        try {
            if (!userRegistered.validate()) return;

            // view 객체 생성
            UserReadModel userReadModel = new UserReadModel();
            // view 객체에 이벤트의 Value 를 set 함
            userReadModel.setUserId(userRegistered.getUserId());
            userReadModel.setEmail(userRegistered.getEmail());
            userReadModel.setNickname(userRegistered.getNickname());
            // view 레파지 토리에 save
            userReadModelRepository.save(userReadModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenUserProfileUpdated_then_UPDATE_1(
        @Payload UserProfileUpdated userProfileUpdated
    ) {
        try {
            if (!userProfileUpdated.validate()) return;
            // view 객체 조회
            Optional<UserReadModel> userReadModelOptional = userReadModelRepository.findByUserId(
                userProfileUpdated.getUserId()
            );

            if (userReadModelOptional.isPresent()) {
                UserReadModel userReadModel = userReadModelOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                userReadModel.setNickname(userProfileUpdated.getNickname());
                // view 레파지 토리에 save
                userReadModelRepository.save(userReadModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenUserDeleted_then_DELETE_1(
        @Payload UserDeleted userDeleted
    ) {
        try {
            if (!userDeleted.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            userReadModelRepository.deleteById(userDeleted.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
