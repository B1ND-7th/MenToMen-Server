package project.bind.MenToMen.domain.comment.domain.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class CommentUpdateDto {

    @NotNull(message = "댓글 ID가 Null일 수 없습니다")
    private Long comment_id;

    @NotBlank(message = "내용이 Null이거나 ' '일 수 없습니다")
    private String content;

}
