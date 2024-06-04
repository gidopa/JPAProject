package Team.project.dto.Logind;

import lombok.Data;

@Data
public class LoginDto {

    private String id; // 로그인하는 아이디 학번 / 교수 번호
    private Long studentId; // 학생pk
    private Long professorId; // 교수 pk
    private String password;
}
