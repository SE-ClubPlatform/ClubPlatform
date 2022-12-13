package SW_Engineering.Group3.repository.Board;

import SW_Engineering.Group3.domain.Board.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}