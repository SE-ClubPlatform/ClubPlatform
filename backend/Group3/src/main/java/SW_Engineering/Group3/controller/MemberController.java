package SW_Engineering.Group3.controller;

import SW_Engineering.Group3.dto.MainResult;
import SW_Engineering.Group3.dto.member.JoinClubDto;
import SW_Engineering.Group3.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(
            value = "유저가 속한 모든 동아리 조회"
    )
    @ApiImplicitParam(
            name = "member_id",
            type = "Long",
            value = "조회를 원하는 유저 번호"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저가 속한 동아리 정보(이름, 카테고리)"),
            @ApiResponse(code = 400, message = "올바르지 않은 유저 번호")
    })
    @GetMapping("/{member_id}/join-clubs")
    public MainResult getJoinClubs(@PathVariable("member_id") Long memberId){

        List<JoinClubDto> userJoinClubs = memberService.getJoinClubs(memberId);

        return new MainResult(userJoinClubs.size(), userJoinClubs);
    }

}
