package SW_Engineering.Group3.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginDto {

    private String email;
    private String password;

    @Builder
    public LoginDto(String email, String password){
        this.email = email;
        this.password = password;
    }
}
