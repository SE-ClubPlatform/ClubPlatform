package SW_Engineering.Group3.controller;

import SW_Engineering.Group3.domain.auth.Authority;
import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.domain.club.ClubMemberList;
import SW_Engineering.Group3.repository.club.ClubMemberRepository;
import SW_Engineering.Group3.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final ClubMemberRepository clubMemberRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/hello/{club_id}")
    public String Hello(Principal principal, @PathVariable Long club_id) {
        Member member = memberRepository.findById(Long.parseLong(principal.getName())).get();
        List<ClubMemberList> clubMemberList = clubMemberRepository.findByMember(member);

        Optional<ClubMemberList> clubMember = clubMemberList.stream()
                .filter(c -> c.getClub().getId() == club_id)
                .findAny();

        ClubMemberList findMember = clubMember.orElseGet(null);

        if(findMember == null || findMember.getAuthority().ordinal() < Authority.ROLE_MANAGER.ordinal()) {
            return "fail";
        } else {
            return "success";
        }
    }

}
