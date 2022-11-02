package SW_Engineering.Group3.repository;

import SW_Engineering.Group3.domian.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<UserSession, Long> {

}
