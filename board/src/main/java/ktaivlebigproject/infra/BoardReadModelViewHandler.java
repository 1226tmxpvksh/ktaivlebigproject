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
public class BoardReadModelViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private BoardReadModelRepository boardReadModelRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPostCreated_then_CREATE_1(
        @Payload PostCreated postCreated
    ) {
        try {
            if (!postCreated.validate()) return;

            // view 객체 생성
            BoardReadModel boardReadModel = new BoardReadModel();
            // view 객체에 이벤트의 Value 를 set 함
            boardReadModel.setBoardId(postCreated.getBoardId());
            boardReadModel.setTitle(postCreated.getTitle());
            boardReadModel.setContent(postCreated.getContent());
            boardReadModel.setAuthorId(postCreated.getAuthorId());
            boardReadModel.setViewCount(0);
            boardReadModel.setType(postCreated.getType());
            boardReadModel.setAttachments(
                String.valueOf(postCreated.getAttachments())
            );
            boardReadModel.setCreatedAt(postCreated.getCreatedAt());
            boardReadModel.setUpdatedAt(postCreated.getCreatedAt());
            // view 레파지 토리에 save
            boardReadModelRepository.save(boardReadModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPostUpdated_then_UPDATE_1(
        @Payload PostUpdated postUpdated
    ) {
        try {
            if (!postUpdated.validate()) return;
            // view 객체 조회
            Optional<BoardReadModel> boardReadModelOptional = boardReadModelRepository.findByBoardId(
                postUpdated.getBoardId()
            );

            if (boardReadModelOptional.isPresent()) {
                BoardReadModel boardReadModel = boardReadModelOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                boardReadModel.setTitle(postUpdated.getTitle());
                boardReadModel.setContent(postUpdated.getContent());
                boardReadModel.setAttachments(
                    String.valueOf(postUpdated.getAttachments())
                );
                boardReadModel.setUpdatedAt(postUpdated.getUpdatedAt());
                // view 레파지 토리에 save
                boardReadModelRepository.save(boardReadModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPostDeleted_then_DELETE_1(
        @Payload PostDeleted postDeleted
    ) {
        try {
            if (!postDeleted.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            boardReadModelRepository.deleteById(postDeleted.getBoardId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
