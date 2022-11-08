package SW_Engineering.Group3.repository.workflow;

import SW_Engineering.Group3.domain.workflow.Phase;
import SW_Engineering.Group3.domain.workflow.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhaseRepository extends JpaRepository<Phase, Long> {

    @Query(value = "SELECT p FROM Phase p WHERE p.clubId = :clubId " +
            "AND p.work = :work " +
            "AND p.step = :step ")
    Phase findPhaseByClubAndWorkId(@Param("clubId") Long clubId,
                                   @Param("work") Work work,
                                   @Param("step") int step);
}
