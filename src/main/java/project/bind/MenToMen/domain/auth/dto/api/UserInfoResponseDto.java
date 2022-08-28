package project.bind.MenToMen.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponseDto {

    private String message;
    private UserInfoDataResponseDto data;
}
