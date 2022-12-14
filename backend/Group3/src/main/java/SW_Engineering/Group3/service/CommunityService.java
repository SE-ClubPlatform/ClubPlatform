package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.Board.Community;
import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.dto.Board.CommunityDto;
import SW_Engineering.Group3.dto.Board.CommunityUpdateDto;
import SW_Engineering.Group3.repository.Board.CommunityRepository;
import SW_Engineering.Group3.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final MemberRepository memberRepository;
    private final CommunityRepository communityRepository;
    private final ClubService clubService;

    public List<Community> getAllCommunity(Long clubId) {
        return communityRepository.findArticlesByClub(clubId);
    }

    public Long createCommunity(CommunityDto communityDto, Long clubId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        Club club = clubService.findClubById(clubId);

        if(member == null) {
            return null;
        }

        Community community = communityDto.toCommunity(member);
        community.setClub(club);
        club.addArticle(community);

        return communityRepository.save(community).getBoardID();
    }

    @Transactional
    public Long updateCommunity(Long id, CommunityUpdateDto communityUpdateDto) {
        Community community = communityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        community.update(communityUpdateDto.getTitle(), communityUpdateDto.getContent());

        return id;
    }

    @Transactional
    public void deleteCommunity(Long id) {
        Community community = communityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        communityRepository.delete(community);
    }

    @Transactional
    public CommunityDto searchById(Long id) {
        Community community = communityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        return new CommunityDto(community.getBoardID(), community.getTitle(),
                community.getAuthor().getUserName(), community.getContent(),
                community.getCategory(), community.getCreateTime());
    }
}
