package SW_Engineering.Group3.dto;

import SW_Engineering.Group3.domian.Member;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UpdateDto {

    @NotNull
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9]+@ajou.ac.kr")
    private String email;
    @NotNull
    private String studentId;
    @NotNull
    private String major;
    @NotNull
    private String phoneNumber;

    public Member toMember() {
        return Member.builder()
                .email(email)
                .studentId(studentId)
                .major(major)
                .phoneNumber(phoneNumber)
                .build();
    }
}
