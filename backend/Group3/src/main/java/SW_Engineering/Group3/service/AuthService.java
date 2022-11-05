package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.auth.UserSession;
import SW_Engineering.Group3.dto.auth.LoginDto;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.dto.auth.SignupDto;
import SW_Engineering.Group3.dto.auth.UpdateDto;
import SW_Engineering.Group3.repository.MemberRepository;
import SW_Engineering.Group3.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        return response.success("회원가입에 성공했습니다");
    }

    @Transactional
    public ResponseEntity login(LoginDto loginDto, boolean fromOauth){

        String loginEmail = loginDto.getEmail();
        String loginPassword = loginDto.getPassword();

        //1. 이메일을 기반으로 유저 검색
        Optional<Member> findMember = memberRepository.findByEmail(loginEmail);

        //2. 유저 정보가 존재하는지 확인
        if(findMember.isPresent()){ // 유저 정보가 존재하면

            // 조건 1. Google Login으로 가입한 User은 비밀번호 검증이 필요없음(구글에서 인증해줬으므로)
            // 조건 2. 일반 이메일 가입 유저는 form에 입력한 비밀번호가 db에 저장된 비밀번호와 동일해야함
            if(fromOauth || passwordEncoder.matches(loginPassword, findMember.get().getPassword())) {
                String sessionId = UUID.randomUUID().toString();
                Long userId = findMember.get().getId();

                UserSession userSession = new UserSession(sessionId, userId);
                sessionRepository.save(userSession); // 세션 생성 후 세션저장소에 저장

                return response.success(sessionId, "로그인에 성공했습니다.", HttpStatus.OK);
            }
            else{
                return response.fail("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
            }
        }
        else{ // 유저 정보가 존재하지 않으면
            return response.fail("존재하지 않는 계정입니다. 다시 확인해주세요", HttpStatus.BAD_REQUEST);
        }

    }
}
