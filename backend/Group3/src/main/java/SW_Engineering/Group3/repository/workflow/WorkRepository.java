package SW_Engineering.Group3.repository.workflow;

import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.domain.workflow.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {

    /**
     * 전달받은 동아리가 가진 모든 업무 목록을 반환
     */
    @Query(value = "SELECT w FROM Work w WHERE w.club = :club")
    List<Work> findWorksByClub(@Param("club") Club club);

}
