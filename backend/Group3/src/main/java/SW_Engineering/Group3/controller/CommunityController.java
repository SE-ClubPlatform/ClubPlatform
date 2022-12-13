package SW_Engineering.Group3.controller;

import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.dto.Board.CommunityDto;
import SW_Engineering.Group3.dto.Board.CommunityUpdateDto;
import SW_Engineering.Group3.dto.Comment.CommunityCommentDto;
import SW_Engineering.Group3.repository.member.MemberRepository;
import SW_Engineering.Group3.service.CommunityCommentService;
import SW_Engineering.Group3.service.CommunityService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;
    private final CommunityCommentService communityCommentService;
    private final MemberRepository memberRepository;

    @ApiOperation(
            value = "소모임 게시글의 모든 정보 반환"
    )
    @ApiImplicitParam(
            name = "club_id",
            type = "Long",
            value = "조회하고자 하는 동아리 번호"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "모든 소모임 게시글 목록 반환"),
            @ApiResponse(code = 400, message = "올바르지 않은 동아리 번호")
    })
    @GetMapping("/club/{club_id}/group")
    public List<CommunityDto> getAllCommunity(@PathVariable("club_id") Long clubId) {
        List<CommunityDto> allCommunity = communityService.getAllCommunity().stream()
                .map(community -> new CommunityDto(community.getBoardID(), community.getTitle(),
                        community.getContent(), community.getCategory()))
                .collect(Collectors.toList());

        return allCommunity;
    }

    @ApiOperation(
            value = "소모임 게시글 작성"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "club_id",
                    type = "Long",
                    value = "소모임 게시글을 작성하고자 하는 동아리 번호"
            ),
            @ApiImplicitParam(
                    name = "communityDto",
                    value = "소모임 게시글 제목, 내용, 저자, 생성날짜, 생성시각, 댓글 수 정보를 포함"
            )
    }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "소모임 게시글 작성")
    })
    @PostMapping("/club/{club_id}/group")
    public Long writeCommunity(@PathVariable("club_id") Long clubId, @RequestBody CommunityDto communityDto) {
        return communityService.createCommunity(communityDto);
    }

    @ApiOperation(
            value = "소모임 게시글 수정"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "club_id",
                    type = "Long",
                    value = "소모임 게시글을 삭제하고자 하는 동아리 번호"
            ),
            @ApiImplicitParam(
                    name = "id",
                    type = "Long",
                    value = "소모임 게시글 번호"
            ),
            @ApiImplicitParam(
                    name = "communityUpdateDto",
                    value = "수정하고자 하는 소모임 게시글 제목, 내용"
            )
    }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "소모임 게시글 수정")
    })
    @PutMapping("/club/{club_id}/group/{id}")
    public Long updateCommunity(@PathVariable("club_id") Long clubId, @PathVariable("id") Long id, @RequestBody CommunityUpdateDto communityUpdateDto) {
        return communityService.updateCommunity(id, communityUpdateDto);
    }

    @ApiOperation(
            value = "소모임 게시글 삭제"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "club_id",
                    type = "Long",
                    value = "소모임 게시글을 삭제하고자 하는 동아리 번호"
            ),
            @ApiImplicitParam(
                    name = "id",
                    type = "Long",
                    value = "소모임 게시글 번호"
            )
    }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "소모임 게시글 삭제")
    })
    @DeleteMapping("club/{club_id}/group/{id}")
    public void deleteCommunity(@PathVariable("club_id") Long clubId, @PathVariable("id") Long id) {
        communityService.deleteCommunity(id);
    }

    @ApiOperation(
            value = "공지사항 조회"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "club_id",
                    type = "Long",
                    value = "소모임 게시글을 삭제하고자 하는 동아리 번호"
            ),
            @ApiImplicitParam(
                    name = "id",
                    type = "Long",
                    value = "소모임 게시글 번호"
            ),
    }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 조회")
    })
    @GetMapping("club/{club_id}/group/{id}")
    public CommunityDto searchById(@PathVariable("club_id") Long clubId, @PathVariable("id") Long id) {
        return communityService.searchById(id);
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
    @GetMapping("/club/{club_id}/community/{id}/comments")
    public List<CommunityCommentDto> getAllComments(@PathVariable("club_id") Long clubId, @PathVariable("id") Long id) {
        return communityCommentService.getComments(id);
    }

    @ApiOperation(value = "댓글 작성", notes = "게시글에 달린 댓글을 작성합니다.")
    @PostMapping("/club/{club_id}/community/{id}/comments")
    public Long writeComment(@PathVariable("club_id") Long clubId, @PathVariable("id") Long id, @RequestBody CommunityCommentDto communityCommentDto) {

        // 로그인 한 멤버 아이디를 넣어야 하는데 방법을 잘 모르겠어요 ㅠㅠ
        Member member = memberRepository.findById(1L).get();
        return communityCommentService.writeComment(id, communityCommentDto, member);
    }

    @ApiOperation(value = "댓글 삭제", notes = "게시글에 달린 댓글을 삭제합니다.")
    @DeleteMapping("/club/{club_id}/community/{id}/comments/{comment_id}")
    public void deleteComment(@PathVariable("id") Long boardId, @PathVariable("comment_id") Long commentId) {
        communityCommentService.deleteComment(commentId);
    }
}
