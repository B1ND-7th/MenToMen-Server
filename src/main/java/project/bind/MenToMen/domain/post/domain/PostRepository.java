package project.bind.MenToMen.domain.post.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.bind.MenToMen.domain.post.domain.entity.Post;
import project.bind.MenToMen.domain.post.domain.entity.Tags;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p join fetch p.user u")
    List<Post> findAll();

    @Query("select p from Post p join fetch p.user u where p.Id=:id")
    Optional<Post> findById(Long id);

    @Query("select p from Post p join fetch p.user u where p.tags=:tag")
    List<Post> findByTag(Tags tag);
}
