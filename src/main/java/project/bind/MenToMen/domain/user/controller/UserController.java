package project.bind.MenToMen.domain.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.bind.MenToMen.domain.user.domain.User;
import project.bind.MenToMen.global.annotation.CheckToken;
import project.bind.MenToMen.global.response.DataResponse;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @CheckToken
    @GetMapping("/my")
    public ResponseEntity<DataResponse<User>> login(@RequestAttribute("user") User user) {
        return DataResponse.ok("유저 정보 조회 성공", user);
    }
}
