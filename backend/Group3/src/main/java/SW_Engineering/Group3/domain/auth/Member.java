package SW_Engineering.Group3.domain.auth;

import SW_Engineering.Group3.domain.club.ClubMemberList;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email; // 로그인 id
    private String password; // 로그인 pw

    @Setter private String userName; // 이름
    @Setter private String studentId; // 학번
    @Setter private String major; // 전공
    @Setter private String phoneNumber; // 핸드폰 번호

    @Setter
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @JsonManagedReference
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ClubMemberList> joinClubs; // 가입한 동아리 목록

    @Builder
    public Member(String email, String password, String userName,
                  String studentId, String major, String phoneNumber, Authority authority){

        this.email = email;
        this.password = password;
        this.userName = userName;
        this.studentId = studentId;
        this.major = major;
        this.phoneNumber = phoneNumber;
        this.authority = authority;

    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
