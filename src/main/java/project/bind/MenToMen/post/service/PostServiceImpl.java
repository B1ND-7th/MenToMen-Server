package project.bind.MenToMen.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bind.MenToMen.post.domain.dto.PostSubmitDto;
import project.bind.MenToMen.post.domain.entities.Post;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Override
    public Post submit(PostSubmitDto postSubmitDto) {
        return null;
    }

}
