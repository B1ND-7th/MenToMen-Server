package project.bind.MenToMen.domain.auth.dto.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DAuthApiRequestDto {

    private String code;
    private String clientId;
    private String clientSecret;
}
