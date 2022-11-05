package SW_Engineering.Group3.domain.auth;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email; // 로그인 id
    private String password; // 로그인 pw
    @Setter private String studentId; // 학번
    @Setter private String major; // 전공
    @Setter private String phoneNumber; // 핸드폰 번호

    @Builder
    public Member(String email, String password, String studentId,
                  String major, String phoneNumber){

        this.email = email;
        this.password = password;
        this.studentId = studentId;
        this.major = major;
        this.phoneNumber = phoneNumber;

    }

}
