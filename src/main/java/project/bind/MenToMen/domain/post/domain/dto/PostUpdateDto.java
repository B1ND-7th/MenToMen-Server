package project.bind.MenToMen.domain.post.domain.dto;

import lombok.Getter;
import project.bind.MenToMen.domain.post.domain.entity.Tag;

@Getter
public class PostUpdateDto {

    private Long postId;
    private String imgUrl;
    private Tag tag;
    private String content;
}