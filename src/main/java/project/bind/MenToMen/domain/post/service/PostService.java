package project.bind.MenToMen.domain.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bind.MenToMen.domain.post.domain.dto.PostSubmitDto;
import project.bind.MenToMen.domain.post.domain.entities.Post;

@Service
@Transactional
public class PostService {

    public Post submit(PostSubmitDto postSubmitDto) {
        return null;
    }

}
