package limjae.week2.service;

import limjae.week2.domain.Posting;
import limjae.week2.domain.PostingRepository;
import limjae.week2.domain.PostingRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostingService {
    private final PostingRepository postingRepository;

    @Transactional
    public Long update(Long id, PostingRequestDTO requestDTO) {
        Posting posting = postingRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물 존재하지 않습니다.")
        );
        posting.update(requestDTO);
        return posting.getPostingSeq();
    }

    @Transactional
    public Posting get(@PathVariable Long id) {
        Posting posting = postingRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물 존재하지 않습니다.")
        );
        return posting;
    }

}
