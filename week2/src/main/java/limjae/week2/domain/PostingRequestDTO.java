package limjae.week2.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostingRequestDTO {
    private String username;
    private String title;
    private String contents;
}
