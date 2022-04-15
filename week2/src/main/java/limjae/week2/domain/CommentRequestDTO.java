package limjae.week2.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDTO {
    private Long postingSeq;
    private String contents;
}
