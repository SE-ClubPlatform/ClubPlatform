package SW_Engineering.Group3.dto.auth;

import SW_Engineering.Group3.domain.auth.Member;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
