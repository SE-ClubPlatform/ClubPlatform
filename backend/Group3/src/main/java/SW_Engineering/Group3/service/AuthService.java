package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domian.Member;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.dto.SignupDto;
import SW_Engineering.Group3.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final Response response;
    private final PasswordEncoder passwordEncoder;

    public Long signupUser(SignupDto signupDto){

        Member member = signupDto.toMember(passwordEncoder);

        if(!memberRepository.existsByEmail(member.getEmail())) {
            Member saveMember = memberRepository.save(member);
            return saveMember.getId();
        }
        else
            return null;
    }

}
