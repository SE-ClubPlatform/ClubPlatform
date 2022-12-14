package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.Board.Anonymous;
import SW_Engineering.Group3.dto.Board.AnonymousDto;
import SW_Engineering.Group3.dto.Board.AnonymousUpdateDto;
import SW_Engineering.Group3.repository.Board.AnonymousRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnonymousService {
    private final AnonymousRepository anonymousRepository;

    public List<Anonymous> getAllAnonymous() {
        return anonymousRepository.findAll();
    }

    @Transactional
    public Long createAnonymous(AnonymousDto anonymousDto) {
        Anonymous anonymous = anonymousDto.toAnonymous();
        return anonymousRepository.save(anonymous).getBoardID();
    }

    @Transactional
    public Long updateAnonymous(Long id, AnonymousUpdateDto anonymousUpdateDto) {
        Anonymous anonymous = anonymousRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        anonymous.update(anonymousUpdateDto.getTitle(), anonymousUpdateDto.getContent());

        return id;
    }

    @Transactional
    public void deleteAnonymous(Long id) {
        Anonymous anonymous = anonymousRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        anonymousRepository.delete(anonymous);
    }

    public AnonymousDto searchById(Long id) {
        Anonymous anonymous = anonymousRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        return new AnonymousDto(anonymous.getBoardID(), anonymous.getTitle(), anonymous.getContent(), anonymous.getIsAnonymous());
    }
}
