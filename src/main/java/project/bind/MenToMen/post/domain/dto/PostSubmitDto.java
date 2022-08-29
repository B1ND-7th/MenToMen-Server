package project.bind.MenToMen.post.domain.dto;

import lombok.Getter;
import project.bind.MenToMen.post.domain.entities.Tags;

import java.time.LocalDateTime;

@Getter
public class PostSubmitDto {

    private Long postId;
    /*private Long uniqueId;*/
    private Tags tags;
    private LocalDateTime dateTime;
    private String content;

}
