package SW_Engineering.Group3.domain.workflow;

import SW_Engineering.Group3.domain.club.Club;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.time.LocalDate;
import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    @OneToMany(mappedBy = "work", cascade = CascadeType.ALL)
    private List<Phase> phases;

    private String title;
    private String introduce;
    private LocalDate finishDate;

    @Builder
    public Work(Club club, String title, String introduce, LocalDate finishDate){
        this.club = club;
        this.title = title;
        this.introduce = introduce;
        this.finishDate = finishDate;
    }

    public void addPhase(Phase phase){
        this.phases.add(phase);
    }

}
