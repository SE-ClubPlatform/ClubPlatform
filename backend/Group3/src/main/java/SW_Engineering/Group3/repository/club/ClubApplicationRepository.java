package SW_Engineering.Group3.repository.club;

import SW_Engineering.Group3.domain.club.ClubApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubApplicationRepository extends JpaRepository<ClubApplication, Long> {
}
