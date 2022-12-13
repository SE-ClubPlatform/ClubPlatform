package SW_Engineering.Group3.repository.Comment;

import SW_Engineering.Group3.domain.comment.AnonymousComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnonymousCommentRepository extends JpaRepository<AnonymousComment, Long> {
    List<AnonymousComment> findAllByBoardId(Long boardId);
}
