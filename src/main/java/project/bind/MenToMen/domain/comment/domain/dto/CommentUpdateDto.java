package project.bind.MenToMen.domain.comment.domain.dto;

import lombok.Getter;
import project.bind.MenToMen.domain.comment.domain.entities.Comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class CommentUpdateDto {

    @NotNull(message = "유저 ID가 Null일 수 없습니다")
    private  Long user_id;

    @NotNull(message = "게시물 ID가 Null일 수 없습니다")
    private Long post_id;

    @NotBlank(message = "내용이 Null이거나 ' '일 수 없습니다")
    private String content;

    public Comment toEntity(CommentUpdateDto commentUpdateDto) {
        return Comment.builder()
                .content(commentUpdateDto.getContent())
                .build();
    }

}
