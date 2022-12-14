package SW_Engineering.Group3.controller;

import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.dto.Board.AnonymousDto;
import SW_Engineering.Group3.dto.Board.AnonymousUpdateDto;
import SW_Engineering.Group3.dto.Comment.AnonymousCommentDto;
import SW_Engineering.Group3.repository.member.MemberRepository;
import SW_Engineering.Group3.service.AnonymousCommentService;
import SW_Engineering.Group3.service.AnonymousService;
import SW_Engineering.Group3.service.MemberService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AnonymousController {
    private final AnonymousService anonymousService;
    private final AnonymousCommentService anonymousCommentService;
    private final MemberService memberService;

    @ApiOperation(
            value = "익명 게시글의 모든 정보 반환"
    )
    @ApiImplicitParam(
            name = "club_id",
            type = "Long",
            value = "조회하고자 하는 동아리 번호"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "모든 익명 게시글 목록 반환"),
            @ApiResponse(code = 400, message = "올바르지 않은 동아리 번호")
    })
    @GetMapping("/club/{club_id}/anonymous")
    public List<AnonymousDto> getAllAnonymous(@PathVariable("club_id") Long clubId) {
        List<AnonymousDto> allAnonymous = anonymousService.getAllAnonymous(clubId).stream()
                .map(anonymous -> new AnonymousDto(anonymous.getBoardID(), anonymous.getTitle(),
                        anonymous.getAuthor().getUserName(), anonymous.getContent(),
                        anonymous.getIsAnonymous(), anonymous.getCreateTime())).collect(Collectors.toList());

        return allAnonymous;
    }

    @ApiOperation(
            value = "익명 게시글 작성"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "club_id",
                    type = "Long",
                    value = "익명 게시글을 작성하고자 하는 동아리 번호"
            ),
            @ApiImplicitParam(
                    name = "communityDto",
                    value = "익명 게시글 제목, 내용, 저자, 생성날짜, 생성시각, 댓글 수 정보를 포함"
            )
    }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "익명 게시글 작성")
    })
    @PostMapping("/club/{club_id}/anonymous")
    public Long writeCommunity(Principal principal, @PathVariable("club_id") Long clubId, @RequestBody AnonymousDto anonymousDto) {
        Long memberId = Long.parseLong(principal.getName());

        if(memberId == null) {
            return null;
        }

        return anonymousService.createAnonymous(anonymousDto, clubId, memberId);
    }

    @ApiOperation(
            value = "익명 게시글 수정"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "club_id",
                    type = "Long",
                    value = "익명 게시글을 삭제하고자 하는 동아리 번호"
            ),
            @ApiImplicitParam(
                    name = "id",
                    type = "Long",
                    value = "익명 게시글 번호"
            ),
            @ApiImplicitParam(
                    name = "communityUpdateDto",
                    value = "수정하고자 하는 익명 게시글 제목, 내용"
            )
    }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "익명 게시글 수정")
    })
    @PutMapping("/club/{club_id}/anonymous/{id}")
    public Long updateAnonymous(@PathVariable("club_id") Long clubId, @PathVariable("id") Long id, @RequestBody AnonymousUpdateDto anonymousUpdateDto) {
        return anonymousService.updateAnonymous(id, anonymousUpdateDto);
    }

    @ApiOperation(
            value = "익명 게시글 삭제"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "club_id",
                    type = "Long",
                    value = "익명 게시글을 삭제하고자 하는 동아리 번호"
            ),
            @ApiImplicitParam(
                    name = "id",
                    type = "Long",
                    value = "익명 게시글 번호"
            )
    }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "익명 게시글 삭제")
    })
    @DeleteMapping("club/{club_id}/anonymous/{id}")
    public void deleteAnonymous(@PathVariable("club_id") Long clubId, @PathVariable("id") Long id) {
        anonymousService.deleteAnonymous(id);
    }

    @ApiOperation(
            value = "익명 게시물 조회"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "club_id",
                    type = "Long",
                    value = "익명 게시물을 삭제하고자 하는 동아리 번호"
            ),
            @ApiImplicitParam(
                    name = "id",
                    type = "Long",
                    value = "익명 게시물 번호"
            ),
    }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "익명 게시물 조회")
    })
    @GetMapping("/club/{club_id}/anonymous/{id}")
    public AnonymousDto searchById(@PathVariable("club_id") Long clubId, @PathVariable("id") Long id) {
        return anonymousService.searchById(id);
    }

    @ApiOperation(
            value = "익명 댓글 조회"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "club_id",
                    type = "Long",
                    value = "익명 게시물에 있는 댓글을 조회하고자 하는 동아리 번호"
            ),
            @ApiImplicitParam(
                    name = "id",
                    type = "Long",
                    value = "익명 게시물 번호"
            ),
    }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "익명 게시물 댓글 조회")
    })
    @GetMapping("/club/{club_id}/anonymous/{id}/comments")
    public List<AnonymousCommentDto> getAllComments(@PathVariable("club_id") Long clubId, @PathVariable("id") Long id) {
        return anonymousCommentService.getComments(id);
    }

    @ApiOperation(value = "댓글 작성", notes = "게시글에 달린 댓글을 작성합니다.")
    @PostMapping("/club/{club_id}/anonymous/{id}/comments")
    public Long writeComment(Principal principal,
                             @PathVariable("club_id") Long clubId, @PathVariable("id") Long id, @RequestBody AnonymousCommentDto anonymousCommentDto) {

        // 로그인 한 멤버 아이디를 넣어야 하는데 방법을 잘 모르겠어요 ㅠㅠ
        Long memberId = Long.parseLong(principal.getName());

        if(memberId == null) {
            return null;
        }

        return anonymousCommentService.writeComment(id, anonymousCommentDto, memberService.findMemberById(memberId));
    }

    @ApiOperation(value = "댓글 삭제", notes = "게시글에 달린 댓글을 삭제합니다.")
    @DeleteMapping("/club/{club_id}/anonymous/{id}/comments/{comment_id}")
    public void deleteComment(@PathVariable("id") Long boardId, @PathVariable("comment_id") Long commentId) {
        anonymousCommentService.deleteComment(commentId);
    }
}
