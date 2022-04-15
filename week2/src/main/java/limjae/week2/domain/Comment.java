package limjae.week2.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Comment extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long commentSeq;

    @Column(nullable = false)
    private Long postingSeq;

    @Column(nullable = false)
    private String contents;

    public Comment(Long postingSeq, String contents) {
        this.postingSeq = postingSeq;
        this.contents = contents;
    }

    public Comment(CommentRequestDTO requestDTO) {
        this.postingSeq = requestDTO.getPostingSeq();
        this.contents = requestDTO.getContents();
    }

    public void update(CommentRequestDTO requestDTO) {
        this.contents = requestDTO.getContents();
    }
}