package project.bind.MenToMen.domain.post.domain.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentDto {

    private Long commentId;
    private LocalDateTime dateTime;
    private String content;

}
