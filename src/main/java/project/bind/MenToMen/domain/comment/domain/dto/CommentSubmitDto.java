package project.bind.MenToMen.domain.comment.domain.dto;

import lombok.Builder;
import lombok.Getter;
import project.bind.MenToMen.domain.comment.domain.entities.Comment;
import project.bind.MenToMen.domain.post.domain.entity.Post;
import project.bind.MenToMen.domain.user.domain.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class CommentSubmitDto {

    @NotNull(message = "게시물 ID가 Null일 수 없습니다")
    private Long postId;

    @NotBlank(message = "내용이 Null이거나 ' '일 수 없습니다")
    private String content;

    public Comment toEntity(User user, Post post, CommentSubmitDto commentSubmitDto) {
        return Comment.builder()
                .user(user)
                .post(post)
                .content(commentSubmitDto.getContent())
                .build();
    }

}
