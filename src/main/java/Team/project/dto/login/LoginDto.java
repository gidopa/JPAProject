package Team.project.dto.login;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @Pattern(regexp = "\\d{8}", message = "8자리 숫자로 입력해주세요")
    private String id;
    private Long studentId;
    private Long professorId;

    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String password;

}
