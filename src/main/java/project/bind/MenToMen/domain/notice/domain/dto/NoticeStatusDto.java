package project.bind.MenToMen.domain.notice.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.bind.MenToMen.domain.notice.domain.entity.NoticeStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class NoticeStatusDto {

    private NoticeStatus noticeStatus;
}
