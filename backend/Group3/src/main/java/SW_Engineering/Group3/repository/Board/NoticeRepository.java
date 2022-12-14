package SW_Engineering.Group3.repository.Board;

import SW_Engineering.Group3.domain.Board.Anonymous;
import SW_Engineering.Group3.domain.Board.Notice;
import SW_Engineering.Group3.domain.club.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query(value = "SELECT n FROM Notice n WHERE n.club.id = :clubId")
    List<Notice> findArticlesByClub(@Param("clubId") Long clubId);
}