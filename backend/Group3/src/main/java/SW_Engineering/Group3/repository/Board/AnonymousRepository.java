package SW_Engineering.Group3.repository.Board;

import SW_Engineering.Group3.domain.Board.Anonymous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnonymousRepository extends JpaRepository<Anonymous, Long> {

    @Query(value = "SELECT a FROM Anonymous a WHERE a.club.id = :clubId")
    List<Anonymous> findArticlesByClub(@Param("clubId") Long clubId);

}