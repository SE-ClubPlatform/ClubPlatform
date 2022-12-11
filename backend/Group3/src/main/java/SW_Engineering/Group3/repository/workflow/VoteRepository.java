package SW_Engineering.Group3.repository.workflow;

import SW_Engineering.Group3.domain.workflow.Phase;
import SW_Engineering.Group3.domain.workflow.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query(value = "SELECT v FROM Vote v WHERE v.phase = :phase")
    Vote findVoteByPhaseId(@Param("phase") Phase phase);

}
