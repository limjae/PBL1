package limjae.week2.controller;

import limjae.week2.domain.CommentRepository;
import limjae.week2.domain.Posting;
import limjae.week2.domain.PostingRepository;
import limjae.week2.domain.PostingRequestDTO;
import limjae.week2.service.CommentService;
import limjae.week2.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostingController {
    private final PostingRepository postingRepository;
    private final PostingService postingService;
    private final CommentService commentService;

//    CREATE
    @PostMapping("/api/posting")
    public Posting createPosting(@RequestBody PostingRequestDTO requestDTO) {
        Posting posting = new Posting(requestDTO);
        return postingRepository.save(posting);
    }

//    READ
    @GetMapping("/api/posting")
    public List<Posting> getPostings() {
        return postingRepository.findAllByOrderByModifiedAtDesc();
    }

    //    READ
    @GetMapping("/api/posting/{id}")
    public Posting getPostings(@PathVariable Long id) {
        Posting posting = postingRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물 존재하지 않습니다.")
        );
        return posting;
    }

//  UPDATE
    @PutMapping("/api/posting/{id}")
    public Long updatePosting(@PathVariable Long id, @RequestBody PostingRequestDTO requestDTO) {
        postingService.update(id, requestDTO);
        return id;
    }

//DELETE
    @DeleteMapping("/api/posting/{id}")
    public Long deletePosting(@PathVariable Long id) {
        postingRepository.deleteById(id);
        commentService.cascadeDelete(id);
        return id;
    }


}
