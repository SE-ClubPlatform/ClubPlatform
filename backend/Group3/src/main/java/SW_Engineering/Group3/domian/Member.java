package SW_Engineering.Group3.domian;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email; // 로그인 id
    private String password; // 로그인 pw
    private String studentId; // 학번
    private String major; // 전공
    private String phoneNumber; // 핸드폰 번호

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
