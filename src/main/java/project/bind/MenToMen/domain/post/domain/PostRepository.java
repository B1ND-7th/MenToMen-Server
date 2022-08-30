package project.bind.MenToMen.domain.post.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import project.bind.MenToMen.domain.user.domain.User;

public interface PostRepository extends JpaRepository<User, Long> {



}
