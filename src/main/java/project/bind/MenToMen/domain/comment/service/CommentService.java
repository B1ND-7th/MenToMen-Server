package project.bind.MenToMen.domain.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bind.MenToMen.domain.comment.domain.CommentRepository;
import project.bind.MenToMen.domain.comment.domain.dto.CommentResponseDto;
import project.bind.MenToMen.domain.comment.domain.dto.CommentSubmitDto;
import project.bind.MenToMen.domain.comment.domain.dto.CommentUpdateDto;
import project.bind.MenToMen.domain.comment.domain.entity.Comment;
import project.bind.MenToMen.domain.post.domain.PostRepository;
import project.bind.MenToMen.domain.post.domain.entity.Post;
import project.bind.MenToMen.domain.user.domain.User;
import project.bind.MenToMen.global.error.CustomError;
import project.bind.MenToMen.global.error.ErrorCode;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAllComment(final Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomError(ErrorCode.NOT_FOUND));
        return commentRepository.findAllByPost(post).stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(User user, CommentSubmitDto commentSubmitDto) {
        Post post = postRepository.findById(commentSubmitDto.getPostId())
                .orElseThrow(() -> new CustomError(ErrorCode.NOT_FOUND));
        commentRepository.save(commentSubmitDto.toEntity(user, post, commentSubmitDto));
    }

    @Transactional
    public void update(User user, CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository.findById(commentUpdateDto.getCommentId())
                .orElseThrow(() -> new CustomError(ErrorCode.NOT_FOUND));
        comment.update(commentUpdateDto);
        commentRepository.save(comment);
    }

    @Transactional
    public void deleteById(User user, final Long id) {
        if (!(commentRepository.existsById(id))) {
            throw CustomError.of(ErrorCode.PARAMETER_IS_BAD);
        }
        commentRepository.deleteById(id);
    }

}
