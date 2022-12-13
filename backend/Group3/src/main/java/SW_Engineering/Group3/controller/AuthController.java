package SW_Engineering.Group3.controller;

import SW_Engineering.Group3.dto.auth.LoginDto;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.dto.auth.SignupDto;
import SW_Engineering.Group3.dto.auth.UpdateDto;
import SW_Engineering.Group3.service.AuthService;
import SW_Engineering.Group3.service.GoogleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final GoogleService googleService;
    private final Response response;

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@Validated @RequestBody SignupDto signupDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            if(bindingResult.getFieldError("email") != null){
                return response.fail("아주대학교 이메일로 회원가입 해주세요.", HttpStatus.BAD_REQUEST);
            }
            return response.fail("회원가입 폼에 유효하지 않은 정보가 있습니다.", HttpStatus.BAD_REQUEST);
        }

        Long saveMemberId = authService.signupUser(signupDto);
        if(saveMemberId != null)
            return response.success("회원가입에 성공했습니다");
        else
            return response.fail("입력하신 이메일은 이미 사용 중입니다.", HttpStatus.BAD_REQUEST);
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginDto loginDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return response.fail("로그인 정보를 모두 입력해주세요", HttpStatus.BAD_REQUEST);

        return authService.login(loginDto, false);

    }

    /**
     * 사용자 정보 수정
     */
    @PostMapping("/update")
    public ResponseEntity<?> updateMember(@Validated @RequestBody UpdateDto updateDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return response.fail("회원가입 정보를 모두 입력해주세요", HttpStatus.BAD_REQUEST);

        return authService.updateMember(updateDto);
    }

    /**
     * 구글 로그인 URL 반환
     * 프론트와 연결하면, Code는 front에서 받게 하고 받은 code를 서버에 Axios 하도록 해야 함.
     */
    @GetMapping("/google/authorization-url")
    public String googleOauthUrl(){
        return googleService.getGoogleUrl();
    }

    /**
     * front로부터 google authorization code를 받은 후 유저 정보를 반환
     */
    @GetMapping("/google/code")
    public ResponseEntity googleCodeCallback(@RequestParam String code) throws IOException {

        // 1. code는 프론트측으로부터 받음

        // 2. code를 이용해 IdToken을 얻어옴
        String idToken = googleService.getIdToken(code);

        // 3. IdToken을 이용해 유저 정보를 얻어옴
        return googleService.getUserProfile(idToken);
    }

    /**
     * 구글로부터 code를 redirect받음
     */
    @GetMapping("/code/google")
    public String googleCallback(@RequestParam Map<String,Object> paramMap) throws IOException {
        String code = String.valueOf(paramMap.get("code"));

        return code;
    }

}
