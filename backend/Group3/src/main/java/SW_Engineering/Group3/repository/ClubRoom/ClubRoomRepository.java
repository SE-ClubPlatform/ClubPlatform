package SW_Engineering.Group3.repository.ClubRoom;

import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.clubroom.ClubRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClubRoomRepository extends JpaRepository<ClubRoom, Long> {

    @Query(value = "SELECT c FROM ClubRoom c WHERE c.club.id = :clubId")
    ClubRoom findByClubId(@Param("clubId") Long clubId);

}
