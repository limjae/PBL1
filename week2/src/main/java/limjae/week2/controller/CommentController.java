package limjae.week2.controller;

import limjae.week2.domain.*;
import limjae.week2.service.CommentService;
import limjae.week2.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    //    CREATE
    @PostMapping("/api/comment")
    public Comment createComment(@RequestBody CommentRequestDTO requestDTO) {
        Comment comment = new Comment(requestDTO);
        return commentRepository.save(comment);
    }

    //    READ
    @GetMapping("/api/comment/{postingSeq}")
    public List<Comment> getComment(@PathVariable Long postingSeq) {
        return commentRepository.findAllByPostingSeqOrderByModifiedAtDesc(postingSeq);
    }

    //  UPDATE
    @PutMapping("/api/comment/{commentId}")
    public Long updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDTO requestDTO) {
        commentService.update(commentId, requestDTO);
        return commentId;
    }

    //DELETE
    @DeleteMapping("/api/comment/{commentId}")
    public Long deleteComment(@PathVariable Long commentId) {
        commentRepository.deleteById(commentId);
        return commentId;
    }


}