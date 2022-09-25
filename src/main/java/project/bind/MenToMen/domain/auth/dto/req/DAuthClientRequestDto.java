package project.bind.MenToMen.domain.auth.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class DAuthClientRequestDto {

    @ApiModelProperty(example = "코드")
    private String code;
}
