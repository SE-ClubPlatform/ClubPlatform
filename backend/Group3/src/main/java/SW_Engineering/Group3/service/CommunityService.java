package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.Board.Community;
import SW_Engineering.Group3.dto.Board.CommunityDto;
import SW_Engineering.Group3.dto.Board.CommunityUpdateDto;
import SW_Engineering.Group3.repository.Board.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;

    public List<Community> getAllCommunity() {
        return communityRepository.findAll();
    }

    public Long createCommunity(CommunityDto communityDto) {
        Community community = communityDto.toCommunity();
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

        return new CommunityDto(community.getTitle(), community.getContent(), community.getAuthor(), community.getCreateDate(), community.getCreateTime(), community.getCommentCount(), community.getCategory());
    }
}
