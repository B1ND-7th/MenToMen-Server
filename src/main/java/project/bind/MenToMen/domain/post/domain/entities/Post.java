package project.bind.MenToMen.domain.post.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.bind.MenToMen.domain.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "post")
@Getter
@NoArgsConstructor
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tags tags;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String content;

    @Builder
    public Post(Long id) {
        this.id = id;
    }
}
