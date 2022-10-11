package project.bind.MenToMen.domain.notice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bind.MenToMen.domain.notice.domain.dto.NoticeResponseDto;
import project.bind.MenToMen.domain.notice.domain.dto.NoticeStatus;
import project.bind.MenToMen.domain.notice.service.NoticeService;
import project.bind.MenToMen.domain.user.domain.User;
import project.bind.MenToMen.global.annotation.CheckToken;
import project.bind.MenToMen.global.response.DataResponse;

import java.util.List;

@RestController
@RequestMapping("/notice")
@AllArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @CheckToken
    @GetMapping("/")
    public ResponseEntity<DataResponse<NoticeStatus>> getNoticeStatus(@RequestAttribute User user) {
        noticeService.noticeStatus(user);
    }

    @RequestMapping("/list/{userId}")
    public ResponseEntity<DataResponse<List<NoticeResponseDto>>> getAlarmList(@PathVariable Long userId) {
        return DataResponse.ok("알림 정보 조회 성공", noticeService.findAllNotice);
    }

}
