package SW_Engineering.Group3.repository.workflow;

import SW_Engineering.Group3.domain.workflow.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhaseRepository extends JpaRepository<Phase, Long> {
}
