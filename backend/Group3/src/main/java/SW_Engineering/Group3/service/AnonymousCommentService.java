package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.Board.Anonymous;
import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.comment.AnonymousComment;
import SW_Engineering.Group3.dto.Comment.AnonymousCommentDto;
import SW_Engineering.Group3.repository.Board.AnonymousRepository;
import SW_Engineering.Group3.repository.Comment.AnonymousCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AnonymousCommentService {
    private final AnonymousRepository anonymousRepository;
    private final AnonymousCommentRepository anonymousCommentRepository;

    @Transactional
    public Long writeComment(Long boardId, AnonymousCommentDto anonymousCommentDto, Member member) {
        AnonymousComment anonymousComment = new AnonymousComment();
        anonymousComment.setContent(anonymousCommentDto.getContent());

        Anonymous anonymous = anonymousRepository.findById(boardId).orElseThrow(() -> {
            return new IllegalArgumentException("게시판을 찾을 수 없습니다.");
        });

        anonymousComment.setMember(member);
        anonymousComment.setPosts(anonymous);

        return anonymousCommentRepository.save(anonymousComment).getId();
    }

    @Transactional
    public List<AnonymousCommentDto> getComments(Long boardId) {
        Anonymous anonymous = anonymousRepository.findById(boardId).get();

        List<AnonymousComment> comments = anonymousCommentRepository.findAllByPosts(anonymous);
        List<AnonymousCommentDto> commentDtos = new ArrayList<>();

        comments.forEach(s -> commentDtos.add(AnonymousCommentDto.toAnonymous(s)));

        return commentDtos;
    }

    @Transactional
    public void deleteComment(Long commentId) {
        AnonymousComment anonymousComment = anonymousCommentRepository.findById(commentId).orElseThrow(() -> {
            return new IllegalArgumentException("댓글 Id를 찾을 수 없습니다.");
        });
        anonymousCommentRepository.delete(anonymousComment);
    }
}
