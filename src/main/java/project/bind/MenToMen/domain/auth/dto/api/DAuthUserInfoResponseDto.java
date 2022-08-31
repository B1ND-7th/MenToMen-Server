package project.bind.MenToMen.domain.auth.dto.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.bind.MenToMen.domain.auth.dto.api.UserInfoDataResponseDto;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponseDto {

    private String message;
    private UserInfoDataResponseDto data;
}
