package ktaivlebigproject.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import ktaivlebigproject.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/boards")
@Transactional
public class BoardController {

    @Autowired
    BoardRepository boardRepository;

    @RequestMapping(
        value = "/boards/{id}/postcreate",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Board postCreate(
        @PathVariable(value = "id") Long id,
        @RequestBody PostCreateCommand postCreateCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /board/postCreate  called #####");
        Optional<Board> optionalBoard = boardRepository.findById(id);

        optionalBoard.orElseThrow(() -> new Exception("No Entity Found"));
        Board board = optionalBoard.get();
        board.postCreate(postCreateCommand);

        boardRepository.save(board);
        return board;
    }

    @RequestMapping(
        value = "/boards/{id}/postdelete",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Board postDelete(
        @PathVariable(value = "id") Long id,
        @RequestBody PostDeleteCommand postDeleteCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /board/postDelete  called #####");
        Optional<Board> optionalBoard = boardRepository.findById(id);

        optionalBoard.orElseThrow(() -> new Exception("No Entity Found"));
        Board board = optionalBoard.get();
        board.postDelete(postDeleteCommand);

        boardRepository.save(board);
        return board;
    }

    @RequestMapping(
        value = "/boards/{id}/downloadfile",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Board downloadFile(
        @PathVariable(value = "id") Long id,
        @RequestBody DownloadFileCommand downloadFileCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /board/downloadFile  called #####");
        Optional<Board> optionalBoard = boardRepository.findById(id);

        optionalBoard.orElseThrow(() -> new Exception("No Entity Found"));
        Board board = optionalBoard.get();
        board.downloadFile(downloadFileCommand);

        boardRepository.save(board);
        return board;
    }

    @RequestMapping(
        value = "/boards/{id}/postupdate",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Board postUpdate(
        @PathVariable(value = "id") Long id,
        @RequestBody PostUpdateCommand postUpdateCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /board/postUpdate  called #####");
        Optional<Board> optionalBoard = boardRepository.findById(id);

        optionalBoard.orElseThrow(() -> new Exception("No Entity Found"));
        Board board = optionalBoard.get();
        board.postUpdate(postUpdateCommand);

        boardRepository.save(board);
        return board;
    }

    @RequestMapping(
        value = "/boards/{id}/postdetail",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Board postDetail(
        @PathVariable(value = "id") Long id,
        @RequestBody PostDetailCommand postDetailCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /board/postDetail  called #####");
        Optional<Board> optionalBoard = boardRepository.findById(id);

        optionalBoard.orElseThrow(() -> new Exception("No Entity Found"));
        Board board = optionalBoard.get();
        board.postDetail(postDetailCommand);

        boardRepository.save(board);
        return board;
    }

    @RequestMapping(
        value = "/boards/{id}/viewcountincrease",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Board viewCountIncrease(
        @PathVariable(value = "id") Long id,
        @RequestBody ViewCountIncreaseCommand viewCountIncreaseCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /board/viewCountIncrease  called #####");
        Optional<Board> optionalBoard = boardRepository.findById(id);

        optionalBoard.orElseThrow(() -> new Exception("No Entity Found"));
        Board board = optionalBoard.get();
        board.viewCountIncrease(viewCountIncreaseCommand);

        boardRepository.save(board);
        return board;
    }
}
//>>> Clean Arch / Inbound Adaptor
