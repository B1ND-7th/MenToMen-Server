package project.bind.MenToMen.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.bind.MenToMen.domain.user.domain.Roles;
import project.bind.MenToMen.domain.user.domain.StdInfo;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponseDto {

    private String name;
    private String email;
    private String profileImage;
    private StdInfo stdInfo;
    private Roles roles;
}
