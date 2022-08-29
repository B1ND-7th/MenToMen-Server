package project.bind.MenToMen.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.bind.MenToMen.post.domain.dto.PostSubmitDto;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/post")
@RequiredArgsConstructor
public class PostController {

    @PostMapping(value = "/submit")
    public String submit(final @Valid @RequestBody PostSubmitDto postSubmitDto) {

        return "";
    }

}
