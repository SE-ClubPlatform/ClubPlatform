package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.auth.UserSession;
import SW_Engineering.Group3.dto.auth.LoginDto;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.dto.auth.SignupDto;
import SW_Engineering.Group3.dto.auth.TokenDto;
import SW_Engineering.Group3.dto.auth.UpdateDto;
import SW_Engineering.Group3.jwt.TokenProvider;
import SW_Engineering.Group3.jwt.token.RefreshToken;
import SW_Engineering.Group3.repository.jwt.RefreshTokenRepository;
import SW_Engineering.Group3.repository.member.MemberRepository;
import SW_Engineering.Group3.repository.member.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final Response response;
    private final PasswordEncoder passwordEncoder;
    private final SessionRepository sessionRepository;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional
    public Long signupUser(SignupDto signupDto){

        Member member = signupDto.toMember(passwordEncoder);

        if(!memberRepository.existsByEmail(member.getEmail())) {
            Member saveMember = memberRepository.save(member);
            return saveMember.getId();
        }
        else
            return null;

    }

    @Transactional
    public ResponseEntity updateMember(UpdateDto updateDto){
        String email = updateDto.getEmail();
        Optional<Member> findMember = memberRepository.findByEmail(email);

        findMember.get().setStudentId(updateDto.getStudentId());
        findMember.get().setMajor(updateDto.getMajor());
        findMember.get().setPhoneNumber(updateDto.getPhoneNumber());

        memberRepository.save(findMember.get());

        return response.success("??????????????? ??????????????????");
    }

    @Transactional
    public ResponseEntity login(LoginDto loginDto, boolean fromOauth){

        String loginEmail = loginDto.getEmail();

        //1. ???????????? ???????????? ?????? ??????
        Optional<Member> findMember = memberRepository.findByEmail(loginEmail);

        //2. ?????? ????????? ??????????????? ??????
        if(!findMember.isPresent()){ // ?????? ????????? ????????????
            return response.fail("???????????? ?????? ???????????????. ?????? ??????????????????", HttpStatus.BAD_REQUEST);
        }

        // 3. Login ID/PW ??? ???????????? AuthenticationToken ??????
        UsernamePasswordAuthenticationToken authenticationToken = loginDto.toAuthentication();

        // 4. ????????? ?????? (????????? ???????????? ??????) ??? ??????????????? ??????
        //    authenticate ???????????? ????????? ??? ??? CustomUserDetailsService ?????? ???????????? loadUserByUsername ???????????? ?????????
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        System.out.println("33333");

        // 5. ?????? ????????? ???????????? JWT ?????? ??????
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        System.out.println("44444");

        // 6. RefreshToken ??????
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 7. ?????? ??????
        return response.success(tokenDto, "????????? ??????!", HttpStatus.OK);

    }
}
