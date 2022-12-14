package SW_Engineering.Group3.controller;

import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.comment.NoticeComment;
import SW_Engineering.Group3.dto.Board.NoticeDto;
import SW_Engineering.Group3.dto.Board.NoticeUpdateDto;
import SW_Engineering.Group3.dto.Comment.AnonymousCommentDto;
import SW_Engineering.Group3.dto.Comment.NoticeCommentDto;
import SW_Engineering.Group3.dto.MainResult;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.repository.member.MemberRepository;
import SW_Engineering.Group3.service.MemberService;
import SW_Engineering.Group3.service.NoticeCommentService;
import SW_Engineering.Group3.service.NoticeService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;
    private final NoticeCommentService noticeCommentService;
    private final MemberService memberService;

    @ApiOperation(
            value = "공지사항의 모든 정보 반환"
    )
    @ApiImplicitParam(
            name = "club_id",
            type = "Long",
            value = "조회하고자 하는 동아리 번호"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "모든 공지사항 목록 반환"),
            @ApiResponse(code = 400, message = "올바르지 않은 동아리 번호")
    })
    @GetMapping("/club/{club_id}/notice")
    public List<NoticeDto> getAllNotice(@PathVariable("club_id") Long clubId) {
        List<NoticeDto> allNotices = noticeService.getAllNotices().stream()
                .map(notice -> new NoticeDto(notice.getBoardID(), notice.getTitle(),
                        notice.getAuthor().getUserName(), notice.getContent(), notice.getIsFinish()))
                .collect(Collectors.toList());
        return allNotices;
    }


    @ApiOperation(
            value = "공지사항 작성"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "club_id",
                    type = "Long",
                    value = "공지사항을 작성하고자 하는 동아리 번호"
            ),
            @ApiImplicitParam(
                    name = "NoticeDto",
                    value = "공지사항 제목, 내용, 저자, 생성날짜, 생성시각, 댓글 수 정보를 포함"
            )
    }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 작성")
    })
    @PostMapping("club/{club_id}/notice")
    public Long writeNotice(Principal principal,
                            @PathVariable("club_id") Long clubId, @RequestBody NoticeDto noticeDto) {
        Long memberId = Long.parseLong(principal.getName());

        if(memberId == null) {
            return null;
        }

        return noticeService.createNotice(noticeDto, memberId);
    }

    @ApiOperation(
            value = "공지사항 수정"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "club_id",
                    type = "Long",
                    value = "공지사항을 삭제하고자 하는 동아리 번호"
            ),
            @ApiImplicitParam(
                    name = "id",
                    type = "Long",
                    value = "공지사항 번호"
            ),
            @ApiImplicitParam(
                    name = "NoticeUpdateDto",
                    value = "수정하고자 하는 공지사항 제목, 내용"
            )
    }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 수정")
    })
    @PutMapping("club/{club_id}/notice/{id}")
    public Long updateNotice(@PathVariable("club_id") Long clubId, @PathVariable("id") Long id, @RequestBody NoticeUpdateDto noticeUpdateDto) {
        return noticeService.updateNotice(id, noticeUpdateDto);
    }

    @ApiOperation(
            value = "공지사항 삭제"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "club_id",
                    type = "Long",
                    value = "공지사항을 삭제하고자 하는 동아리 번호"
            ),
            @ApiImplicitParam(
                    name = "id",
                    type = "Long",
                    value = "공지사항 번호"
            ),
    }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 삭제")
    })
    @DeleteMapping("club/{club_id}/notice/{id}")
    public void deleteNotice(@PathVariable("club_id") Long clubId, @PathVariable("id") Long id) {
        noticeService.deleteNotice(id);
    }

    @ApiOperation(
            value = "공지사항 조회"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "club_id",
                    type = "Long",
                    value = "공지사항을 삭제하고자 하는 동아리 번호"
            ),
            @ApiImplicitParam(
                    name = "id",
                    type = "Long",
                    value = "공지사항 번호"
            ),
    }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 조회")
    })
    @GetMapping("club/{club_id}/notice/{id}")
    public NoticeDto searchById(@PathVariable("club_id") Long clubId, @PathVariable("id") Long id) {
        return noticeService.searchById(id);
    }

    @ApiOperation(
            value = "공지사항 게시물 댓글 조회"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "club_id",
                    type = "Long",
                    value = "공지사항 게시물에 있는 댓글을 조회하고자 하는 동아리 번호"
            ),
            @ApiImplicitParam(
                    name = "id",
                    type = "Long",
                    value = "공지사항 게시물 번호"
            ),
    }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 게시물 댓글 조회")
    })
    @GetMapping("/club/{club_id}/notice/{id}/comments")
    public List<NoticeCommentDto> getAllComments(@PathVariable("club_id") Long clubId, @PathVariable("id") Long id) {
        return noticeCommentService.getComments(id);
    }

    @ApiOperation(value = "댓글 작성", notes = "게시글에 달린 댓글을 작성합니다.")
    @PostMapping("/club/{club_id}/notice/{id}/comments")
    public Long writeComment(Principal principal,
                             @PathVariable("club_id") Long clubId, @PathVariable("id") Long id, @RequestBody NoticeCommentDto noticeCommentDto) {

        Long memberId = Long.parseLong(principal.getName());

        if(memberId == null) {
            return null;
        }

        // 로그인 한 멤버 아이디를 넣어야 하는데 방법을 잘 모르겠어요 ㅠㅠ
        Member member = memberService.findMemberById(memberId);

        return noticeCommentService.writeComment(id, noticeCommentDto, member);
    }

    @ApiOperation(value = "댓글 삭제", notes = "게시글에 달린 댓글을 삭제합니다.")
    @DeleteMapping("/club/{club_id}/notice/{id}/comments/{comment_id}")
    public void deleteComment(@PathVariable("id") Long boardId, @PathVariable("comment_id") Long commentId) {
        noticeCommentService.deleteComment(commentId);
    }
}
