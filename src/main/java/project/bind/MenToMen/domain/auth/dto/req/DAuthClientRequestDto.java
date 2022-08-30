package project.bind.MenToMen.domain.auth.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DAuthClientRequestDto {

    @ApiModelProperty(example = "코드")
    private String code;
}
