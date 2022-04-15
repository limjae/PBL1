package limjae.week2.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Posting extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long postingSeq;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    public Posting(PostingRequestDTO requestDTO) {
        this.username = requestDTO.getUsername();
        this.title = requestDTO.getTitle();
        this.contents = requestDTO.getContents();
    }

    public void update(PostingRequestDTO requestDTO) {
        this.username = requestDTO.getUsername();
        this.title = requestDTO.getTitle();
        this.contents = requestDTO.getContents();
    }
}