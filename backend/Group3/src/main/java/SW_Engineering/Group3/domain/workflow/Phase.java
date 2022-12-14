package SW_Engineering.Group3.domain.workflow;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Phase {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Work work;
    @Column private Long clubId;
    @Column private String title;
    @Column private String content;
    @Column private int step;
    @Column private String finishDate;
    @Column private boolean voteActivate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Vote vote;

    @Builder
    public Phase(Work work, Long clubId, String title, String content,
                 int step, String finishDate, boolean voteActivate, Vote vote) {

        this.work = work;
        this.clubId = clubId;
        this.title = title;
        this.content = content;
        this.step = step;
        this.finishDate = finishDate;
        this.voteActivate = voteActivate;
        this.vote = vote;
    }

}
