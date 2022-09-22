package project.bind.MenToMen.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bind.MenToMen.domain.comment.domain.dto.CommentSubmitDto;
import project.bind.MenToMen.domain.comment.domain.dto.CommentUpdateDto;
import project.bind.MenToMen.domain.comment.domain.entities.Comment;
import project.bind.MenToMen.domain.comment.service.CommentService;
import project.bind.MenToMen.domain.user.domain.User;
import project.bind.MenToMen.global.response.DataResponse;
import project.bind.MenToMen.global.response.Response;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/read-all/{postId}")
    public ResponseEntity<DataResponse<List<Comment>>> getComment(@PathVariable Long postId) {
        return DataResponse.ok("게시물 댓글 정보 조회 성공",  commentService.findCommentAll(postId));
    }

    @PostMapping("/submit")
    public ResponseEntity<Response> postComment(
            final @RequestAttribute User user,
            final @Valid @RequestBody CommentSubmitDto commentSubmitDto
    ) {
        commentService.save(user, commentSubmitDto);
        return Response.ok("글 등록 성공");
    }

    @PatchMapping("/update")
    public ResponseEntity<Response> patchComment(
            final @Valid @RequestBody CommentUpdateDto commentUpdateDto
    ) {
        commentService.update(commentUpdateDto);
        return Response.ok("댓글 수정 성공");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteComment(@PathVariable Long id) {
        commentService.deleteById(id);
        return Response.ok("댓글 삭제 성공");
    }

}
