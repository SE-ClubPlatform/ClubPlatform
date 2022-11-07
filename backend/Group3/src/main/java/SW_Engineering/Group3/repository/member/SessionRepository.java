package SW_Engineering.Group3.repository.member;

import SW_Engineering.Group3.domain.auth.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<UserSession, Long> {

}
