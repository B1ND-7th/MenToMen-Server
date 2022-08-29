package project.bind.MenToMen.post.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "post")
@Getter
@NoArgsConstructor
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

//    @ManyToOne()
//    private Long uniqueId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tags tags;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String content;
}
