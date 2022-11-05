package SW_Engineering.Group3.repository;

import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.domain.club.ClubMemberList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClubMemberRepository extends JpaRepository<ClubMemberList, Long> {

    /**
     * 동아리-유저 리스트에서, 전달받은 동아리에 속하는 모든 유저 정보를 반환
     */
    @Query(value = "SELECT c FROM ClubMemberList c JOIN FETCH c.member WHERE c.club = :club")
    List<ClubMemberList> findByClubId(@Param("club") Club club);

    /**
     * 동아리-유저 리스트에서, 전달받은 유저가 속한 모든 동아리 정보를 반환
     */
    @Query(value = "SELECT c FROM ClubMemberList c JOIN FETCH c.club WHERE c.member = :member")
    List<ClubMemberList> findByMemberId(@Param("member") Member member);

}
