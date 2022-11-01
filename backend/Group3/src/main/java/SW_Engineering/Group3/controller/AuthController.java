package SW_Engineering.Group3.controller;

import SW_Engineering.Group3.domian.Member;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.dto.SignupDto;
import SW_Engineering.Group3.service.AuthService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            return response.fail("입력 폼에 유효하지 않은 정보가 있습니다.", HttpStatus.BAD_REQUEST);
        }

        Long saveMemberId = authService.signupUser(signupDto);
        if(saveMemberId != null)
            return response.success("회원가입에 성공했습니다");
        else
            return response.fail("입력하신 이메일은 이미 사용 중입니다.", HttpStatus.BAD_REQUEST);
    }
}
