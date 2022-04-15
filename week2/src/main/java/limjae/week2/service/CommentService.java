package limjae.week2.service;

import limjae.week2.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Long update(Long id, CommentRequestDTO requestDTO) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물 존재하지 않습니다.")
        );
        comment.update(requestDTO);
        return comment.getCommentSeq();
    }

    @Transactional
    public void cascadeDelete(Long id){
        commentRepository.deleteByPostingSeq(id);
    }

}