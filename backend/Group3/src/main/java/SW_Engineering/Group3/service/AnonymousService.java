package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.Board.Anonymous;
import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.dto.Board.AnonymousDto;
import SW_Engineering.Group3.dto.Board.AnonymousUpdateDto;
import SW_Engineering.Group3.repository.Board.AnonymousRepository;
import SW_Engineering.Group3.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnonymousService {

    private final MemberRepository memberRepository;
    private final AnonymousRepository anonymousRepository;
    private final ClubService clubService;

    public List<Anonymous> getAllAnonymous() {
        return anonymousRepository.findAll();
    }

    @Transactional
    public Long createAnonymous(AnonymousDto anonymousDto, Long clubId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        Club club = clubService.findClubById(clubId);

        if(member == null) {
            return null;
        }

        Anonymous anonymous = anonymousDto.toAnonymous(member);
        anonymous.setClub(club);
        club.addArticle(anonymous);

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

        return new AnonymousDto(anonymous.getBoardID(), anonymous.getTitle(), anonymous.getAuthor().getUserName(),
                anonymous.getContent(), anonymous.getIsAnonymous());
    }
}
