package project.bind.MenToMen.domain.auth.dto.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DAuthUserInfoResponseDto {

    private String message;
    private DAuthUserInfoDataResponseDto data;
}
