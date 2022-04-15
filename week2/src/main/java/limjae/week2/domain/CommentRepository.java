package limjae.week2.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostingSeqOrderByModifiedAtDesc(Long id);
    void deleteByPostingSeq(Long id);
}