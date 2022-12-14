package SW_Engineering.Group3.domain.club;

import SW_Engineering.Group3.domain.auth.Authority;
import SW_Engineering.Group3.domain.auth.Member;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ClubMemberList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Member member;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @Setter
    private Authority authority;

    public ClubMemberList(Member member, Club club, Authority authority){
        this.member = member;
        this.club = club;
        this.authority = authority;
    }

}
