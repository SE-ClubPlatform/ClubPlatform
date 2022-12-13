package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.Board.Anonymous;
import SW_Engineering.Group3.dto.Board.AnonymousDto;
import SW_Engineering.Group3.dto.Board.AnonymousUpdateDto;
import SW_Engineering.Group3.repository.Board.AnonymousRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnonymousService {
    private final AnonymousRepository anonymousRepository;

    public List<Anonymous> getAllAnonymous() {
        return anonymousRepository.findAll();
    }

    public Long createAnonymous(AnonymousDto anonymousDto) {
        Anonymous anonymous = anonymousDto.toAnonymous();
        return anonymousRepository.save(anonymous).getBoardID();
    }

    @Transactional
    public Long updateAnonymous(Long id, AnonymousUpdateDto anonymousUpdateDto) {
        Anonymous anonymous = anonymousRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        anonymous.update(anonymous.getTitle(), anonymous.getContent());

        return id;
    }

    @Transactional
    public void deleteAnonymous(Long id) {
        Anonymous anonymous = anonymousRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        anonymousRepository.delete(anonymous);
    }

    @Transactional
    public AnonymousDto searchById(Long id) {
        Anonymous anonymous = anonymousRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        return new AnonymousDto(anonymous.getTitle(), anonymous.getContent(), anonymous.getAuthor(), anonymous.getCreateDate(), anonymous.getCreateTime(), anonymous.getCommentCount(), anonymous.getIsAnonymous());
    }
}
