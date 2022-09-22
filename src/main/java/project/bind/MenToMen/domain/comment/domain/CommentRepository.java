package project.bind.MenToMen.domain.comment.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import project.bind.MenToMen.domain.comment.domain.entities.Comment;
import project.bind.MenToMen.domain.post.domain.entities.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);

}
