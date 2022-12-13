package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.Board.Notice;
import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.comment.NoticeComment;
import SW_Engineering.Group3.dto.Comment.NoticeCommentDto;
import SW_Engineering.Group3.repository.Board.NoticeRepository;
import SW_Engineering.Group3.repository.Comment.NoticeCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeCommentService {
    private final NoticeRepository noticeRepository;
    private final NoticeCommentRepository noticeCommentRepository;

    @Transactional
    public Long writeComment(Long boardId, NoticeCommentDto noticeCommentDto, Member member) {
        NoticeComment noticeComment = new NoticeComment();
        noticeComment.setContent(noticeCommentDto.getContent());

        Notice notice = noticeRepository.findById(boardId).orElseThrow(() -> {
            return new IllegalArgumentException("게시판을 찾을 수 없습니다.");
        });

        noticeComment.setMember(member);
        noticeComment.setPosts(notice);

        return noticeCommentRepository.save(noticeComment).getId();
    }

    @Transactional
    public List<NoticeCommentDto> getComments(Long boardId) {
        Notice findNotice = noticeRepository.findById(boardId).get();

        List<NoticeComment> comments = noticeCommentRepository.findAllByPosts(findNotice);
        List<NoticeCommentDto> commentDtos = new ArrayList<>();

        comments.forEach(s -> commentDtos.add(NoticeCommentDto.toNotice(s)));

        return commentDtos;
    }

    @Transactional
    public void deleteComment(Long commentId) {
        NoticeComment noticeComment = noticeCommentRepository.findById(commentId).orElseThrow(() -> {
            return new IllegalArgumentException("댓글 Id를 찾을 수 없습니다.");
        });
        noticeCommentRepository.delete(noticeComment);
    }
}
