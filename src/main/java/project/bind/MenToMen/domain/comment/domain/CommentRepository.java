package project.bind.MenToMen.domain.comment.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import project.bind.MenToMen.domain.comment.domain.entity.Comment;
import project.bind.MenToMen.domain.post.domain.entity.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);

}
