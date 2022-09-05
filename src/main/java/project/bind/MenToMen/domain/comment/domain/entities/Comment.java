package project.bind.MenToMen.domain.comment.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import project.bind.MenToMen.domain.post.domain.entities.Post;
import project.bind.MenToMen.domain.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "comment")
@Getter
@NoArgsConstructor
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String content;

}
