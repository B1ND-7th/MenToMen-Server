package project.bind.MenToMen.domain.post.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import project.bind.MenToMen.domain.post.domain.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
