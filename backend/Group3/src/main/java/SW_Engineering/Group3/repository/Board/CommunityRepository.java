package SW_Engineering.Group3.repository.Board;

import SW_Engineering.Group3.domain.Board.Anonymous;
import SW_Engineering.Group3.domain.Board.Community;
import SW_Engineering.Group3.domain.club.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Long> {

    @Query(value = "SELECT c FROM Community c WHERE c.club.id = :clubId")
    List<Community> findArticlesByClub(@Param("clubId") Long clubId);

}