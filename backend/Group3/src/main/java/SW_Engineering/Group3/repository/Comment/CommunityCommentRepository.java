package SW_Engineering.Group3.repository.Comment;

import SW_Engineering.Group3.domain.Board.Community;
import SW_Engineering.Group3.domain.comment.CommunityComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityCommentRepository extends JpaRepository<CommunityComment, Long> {
    List<CommunityComment> findAllByPosts(Community community);
}
