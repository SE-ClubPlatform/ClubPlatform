package SW_Engineering.Group3.domain.club;

import SW_Engineering.Group3.domain.auth.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ClubMemberList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    private Authority authority;

}
