package project.bind.MenToMen.domain.post.dto;

import lombok.Getter;
import project.bind.MenToMen.domain.post.domain.entity.Tags;

@Getter
public class PostUpdateDto {

    private Long postId;
    private String imgUrl;
    private Tags tags;
    private String content;
}