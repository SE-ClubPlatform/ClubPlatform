package SW_Engineering.Group3.repository.club;

import SW_Engineering.Group3.domain.club.ClubAuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TokenRepository extends JpaRepository<ClubAuthToken, Long> {

    @Query(value = "SELECT c FROM ClubAuthToken c WHERE c.clubName = :clubName AND c.presidentName = :presidentName")
    ClubAuthToken findTokenByNameAndPresident(@Param("clubName") String clubName,
                                              @Param("presidentName") String presidentName);
}
