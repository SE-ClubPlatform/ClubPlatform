package SW_Engineering.Group3.domain.workflow;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Phase {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Work work;

    private Long clubId;
    private String title;
    private String content;
    private int currentStep;
    private LocalDateTime deadLine;
    private boolean isFinish;

    @Builder
    public Phase(Work work, Long clubId, String title, String content,
                 int currentStep, LocalDateTime deadLine, boolean isFinish) {

        this.work = work;
        this.clubId = clubId;
        this.title = title;
        this.content = content;
        this.currentStep = currentStep;
        this.deadLine = deadLine;
        this.isFinish = isFinish;

    }

}
