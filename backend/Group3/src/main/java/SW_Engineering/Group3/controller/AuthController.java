package SW_Engineering.Group3.controller;

import SW_Engineering.Group3.domian.Member;
import SW_Engineering.Group3.dto.LoginDto;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.dto.SignupDto;
import SW_Engineering.Group3.service.AuthService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final Response response;
    private final PasswordEncoder passwordEncoder;


    @ApiOperation(
            value = "사용자 회원가입",
            notes = "사용자의 이메일을 기준으로 회원가입을 시킨다"
    )
    @ApiImplicitParam(
            name = "signupDto",
            value = "사용자가 입력하는 회원가입 form"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입 성공"),
            @ApiResponse(code = 400, message = "이메일 중복 혹은 필드값 오류로 회원가입 실패")
    })
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

    @ApiOperation(
            value = "사용자 로그인",
            notes = "아주대학교 이메일과 비밀번호를 이용해 로그인을 시킴"
    )
    @ApiImplicitParam(
            name = "loginDto",
            value = "사용자가 입력하는 로그인 form"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 성공 및 SessionId 발급"),
            @ApiResponse(code = 400, message = "올바르지 않은 아이디(이메일) 입력\n올바르지 않은 비밀번호 입력\n로그인 정보를 모두 입력하지 않음")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginDto loginDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return response.fail("로그인 정보를 모두 입력해주세요", HttpStatus.BAD_REQUEST);

        return authService.login(loginDto);

    }
}
