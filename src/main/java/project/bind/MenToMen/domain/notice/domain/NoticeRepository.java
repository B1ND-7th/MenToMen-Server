package project.bind.MenToMen.domain.notice.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import project.bind.MenToMen.domain.notice.domain.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
