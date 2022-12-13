package SW_Engineering.Group3.repository.Comment;

import SW_Engineering.Group3.domain.Board.Notice;
import SW_Engineering.Group3.domain.comment.NoticeComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeCommentRepository extends JpaRepository<NoticeComment, Long> {
    List<NoticeComment> findAllByPosts(Notice notice);
}
