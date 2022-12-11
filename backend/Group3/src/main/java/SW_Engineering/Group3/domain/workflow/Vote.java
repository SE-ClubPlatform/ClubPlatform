package SW_Engineering.Group3.domain.workflow;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Vote {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private LocalDate finishDate;

    @Setter
    @OneToOne(mappedBy = "vote", fetch = FetchType.LAZY)
    private Phase phase;

    @OneToMany(mappedBy = "vote", cascade = CascadeType.ALL)
    private List<VoteContent> contents = new ArrayList<>();

    public Vote(String title, LocalDate finishDate) {
        this.title = title;
        this.finishDate = finishDate;
    }

    public void addVoteContent(VoteContent voteContent) {
        this.contents.add(voteContent);
        voteContent.setVote(this);
    }

}
