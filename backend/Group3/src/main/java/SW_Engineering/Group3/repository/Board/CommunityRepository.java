package SW_Engineering.Group3.repository.Board;

import SW_Engineering.Group3.domain.Board.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {
}