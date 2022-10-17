package project.bind.MenToMen.domain.notice.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bind.MenToMen.domain.notice.domain.dto.NoticeResponseDto;
import project.bind.MenToMen.domain.notice.domain.dto.NoticeStatusDto;
import project.bind.MenToMen.domain.notice.service.NoticeService;
import project.bind.MenToMen.domain.user.domain.User;
import project.bind.MenToMen.global.annotation.CheckToken;
import project.bind.MenToMen.global.response.DataResponse;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @CheckToken
    @ApiOperation(value = "알림 여부 존재하면 EXIST 없으면 NONE")
    @GetMapping("/check")
    public ResponseEntity<DataResponse<NoticeStatusDto>> getNoticeStatus(@RequestAttribute User user) {
        return DataResponse.ok("알림 여부 조회 성공", noticeService.getNoticeStatus(user));
    }

    @CheckToken
    @ApiOperation(value = "알림 조회")
    @GetMapping("/list")
    public ResponseEntity<DataResponse<List<NoticeResponseDto>>> getNoticeList(@RequestAttribute User user) {
        return DataResponse.ok("알림 정보 조회 성공", noticeService.findAllNotice(user));
    }

}
