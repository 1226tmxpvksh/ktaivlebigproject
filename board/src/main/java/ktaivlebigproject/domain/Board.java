package ktaivlebigproject.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import ktaivlebigproject.BoardApplication;
import lombok.Data;

@Entity
@Table(name = "Board_table")
@Data
//<<< DDD / Aggregate Root
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardId;

    private String title;

    private String content;

    private Long authorId;

    private Integer viewCount;

    private String type;

    @Embedded
    private File attachments;

    private Date createdAt;

    private Date updatedAt;

    public static BoardRepository repository() {
        BoardRepository boardRepository = BoardApplication.applicationContext.getBean(
            BoardRepository.class
        );
        return boardRepository;
    }

    //<<< Clean Arch / Port Method
    public void postCreate(PostCreateCommand postCreateCommand) {
        //implement business logic here:

        PostCreated postCreated = new PostCreated(this);
        postCreated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void postDelete(PostDeleteCommand postDeleteCommand) {
        //implement business logic here:

        PostDeleted postDeleted = new PostDeleted(this);
        postDeleted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void downloadFile(DownloadFileCommand downloadFileCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void postUpdate(PostUpdateCommand postUpdateCommand) {
        //implement business logic here:

        PostUpdated postUpdated = new PostUpdated(this);
        postUpdated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void postDetail(PostDetailCommand postDetailCommand) {
        //implement business logic here:

        PostDetailed postDetailed = new PostDetailed(this);
        postDetailed.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void viewCountIncrease(
        ViewCountIncreaseCommand viewCountIncreaseCommand
    ) {
        //implement business logic here:

        ViewCountIncreased viewCountIncreased = new ViewCountIncreased(this);
        viewCountIncreased.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void viewCountPolicy(PostDetailed postDetailed) {
        //implement business logic here:

        /** Example 1:  new item 
        Board board = new Board();
        repository().save(board);

        */

        /** Example 2:  finding and process
        

        repository().findById(postDetailed.get???()).ifPresent(board->{
            
            board // do something
            repository().save(board);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void reportSavePolicy(ReportSaved reportSaved) {
        //implement business logic here:

        /** Example 1:  new item 
        Board board = new Board();
        repository().save(board);

        */

        /** Example 2:  finding and process
        

        repository().findById(reportSaved.get???()).ifPresent(board->{
            
            board // do something
            repository().save(board);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
