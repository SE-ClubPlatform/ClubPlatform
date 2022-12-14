package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.Board.Community;
import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.comment.CommunityComment;
import SW_Engineering.Group3.dto.Comment.CommunityCommentDto;
import SW_Engineering.Group3.repository.Board.CommunityRepository;
import SW_Engineering.Group3.repository.Comment.CommunityCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommunityCommentService {
    private final CommunityRepository communityRepository;
    private final CommunityCommentRepository communityCommentRepository;

    @Transactional
    public Long writeComment(Long boardId, CommunityCommentDto CommunityCommentDto, Member member) {
        CommunityComment CommunityComment = new CommunityComment();
        CommunityComment.setContent(CommunityCommentDto.getContent());

        Community Community = communityRepository.findById(boardId).orElseThrow(() -> {
            return new IllegalArgumentException("게시판을 찾을 수 없습니다.");
        });

        CommunityComment.setMember(member);
        CommunityComment.setPosts(Community);

        return communityCommentRepository.save(CommunityComment).getId();
    }

    @Transactional
    public List<CommunityCommentDto> getComments(Long boardId) {
        Community community = communityRepository.findById(boardId).get();

        List<CommunityComment> comments = communityCommentRepository.findAllByPosts(community);
        List<CommunityCommentDto> commentDtos = new ArrayList<>();

        comments.forEach(s -> commentDtos.add(CommunityCommentDto.toCommunity(s)));

        return commentDtos;
    }

    @Transactional
    public void deleteComment(Long commentId) {
        CommunityComment CommunityComment = communityCommentRepository.findById(commentId).orElseThrow(() -> {
            return new IllegalArgumentException("댓글 Id를 찾을 수 없습니다.");
        });
        communityCommentRepository.delete(CommunityComment);
    }
}
