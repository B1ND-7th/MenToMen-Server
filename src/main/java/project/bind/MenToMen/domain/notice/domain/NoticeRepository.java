package project.bind.MenToMen.domain.notice.domain;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import project.bind.MenToMen.domain.notice.domain.entity.Notice;
import project.bind.MenToMen.domain.user.domain.User;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @EntityGraph(attributePaths = {"sendUser", "post", "comment"})
    List<Notice> findAllByWriterUser(User writerUser, Sort sort);
}
