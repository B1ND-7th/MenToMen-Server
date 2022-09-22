package project.bind.MenToMen.domain.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bind.MenToMen.domain.comment.domain.CommentRepository;
import project.bind.MenToMen.domain.comment.domain.dto.CommentSubmitDto;
import project.bind.MenToMen.domain.comment.domain.dto.CommentUpdateDto;
import project.bind.MenToMen.domain.comment.domain.entities.Comment;
import project.bind.MenToMen.domain.post.domain.PostRepository;
import project.bind.MenToMen.domain.post.domain.entities.Post;
import project.bind.MenToMen.domain.user.domain.User;
import project.bind.MenToMen.global.error.CustomError;
import project.bind.MenToMen.global.error.ErrorCode;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<Comment> findCommentAll(final Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomError(ErrorCode.NOT_FOUND));
        return null;
    }

    @Transactional(readOnly = true)
    public void save(User user, CommentSubmitDto commentSubmitDto) {
        Post post = postRepository.findById(commentSubmitDto.getPost_id())
                        .orElseThrow(() -> new CustomError(ErrorCode.NOT_FOUND));
        commentRepository.save(commentSubmitDto.toEntity(user, commentSubmitDto));
    }

    @Transactional(readOnly = true)
    public void update(CommentUpdateDto commentUpdateDto) {
        Post post = postRepository.findById(commentUpdateDto.getPost_id())
                .orElseThrow(() -> new CustomError(ErrorCode.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public void deleteById(final Long id) {
        if (!commentRepository.existsById(id)) throw CustomError.of(ErrorCode.PARAMETER_IS_BAD);
        commentRepository.deleteById(id);
    }

}
