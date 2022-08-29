package project.bind.MenToMen.post.service;

import project.bind.MenToMen.post.domain.dto.PostSubmitDto;
import project.bind.MenToMen.post.domain.entities.Post;

public interface PostService {

    Post submit(PostSubmitDto postSubmitDto);

}
