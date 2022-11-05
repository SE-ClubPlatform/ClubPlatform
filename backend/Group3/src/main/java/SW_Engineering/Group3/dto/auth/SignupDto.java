package SW_Engineering.Group3.dto.auth;

import SW_Engineering.Group3.domain.auth.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupDto {
    
    @NotNull
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9]+@ajou.ac.kr")
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String studentId;
    @NotNull
    private String major;
    @NotNull
    private String phoneNumber;

    public Member toMember(PasswordEncoder passwordEncoder) {

        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .studentId(studentId)
                .major(major)
                .phoneNumber(phoneNumber)
                .build();
    }

    @Builder
    public SignupDto(String email, String password, String studentId, String major, String phoneNumber){
        this.email = email;
        this.password = password;
        this.studentId = studentId;
        this.major = major;
        this.phoneNumber = phoneNumber;
    }
}
