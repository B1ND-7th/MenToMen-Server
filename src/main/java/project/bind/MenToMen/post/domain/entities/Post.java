package project.bind.MenToMen.post.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

//    @ManyToOne()
//    private Long uniqueId;

    @Enumerated(EnumType.STRING)
    private Tags tags;

    private LocalDateTime dateTime;

    @Column()
    private String content;
}
