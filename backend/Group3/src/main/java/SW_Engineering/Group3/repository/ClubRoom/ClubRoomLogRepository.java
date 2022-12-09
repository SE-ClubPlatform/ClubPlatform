package SW_Engineering.Group3.repository.ClubRoom;

import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.clubroom.ClubRoom;
import SW_Engineering.Group3.domain.clubroom.ClubRoomLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRoomLogRepository extends JpaRepository<ClubRoomLog, Long> {

    @Query(value = "SELECT c.type FROM ClubRoomLog c " +
            "WHERE c.clubRoom = :clubRoom " +
            "AND c.member = :member " +
            "ORDER BY c.time DESC")
    List<String> findMemberEnterType(@Param("clubRoom") ClubRoom clubRoom,
                               @Param("member") Member member,
                               Pageable pageable);

}
