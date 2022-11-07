package SW_Engineering.Group3.repository.workflow;

import SW_Engineering.Group3.domain.workflow.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
}
