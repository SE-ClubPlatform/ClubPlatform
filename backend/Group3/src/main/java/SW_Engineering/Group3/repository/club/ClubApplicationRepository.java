package SW_Engineering.Group3.repository.club;

import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.domain.club.ClubApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubApplicationRepository extends JpaRepository<ClubApplication, Long> {

    /**
     * 유저와 동아리 정보를 기반으로 동아리 가입 신청 로그를 찾는다
     */
    @Query(value = "SELECT c FROM ClubApplication c WHERE c.club = :club AND c.member = :member")
    ClubApplication findByClubAndMember(@Param("club") Club club,
                                        @Param("member") Member member);
}
