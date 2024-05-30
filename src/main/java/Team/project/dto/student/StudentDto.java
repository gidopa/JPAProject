package Team.project.dto.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private Long student_id;
    private Long hakbun;
    private String name;
    private String password;
    private Long major_id;
    private String city;
    private String street;
    private int years;
    private int semester;
    private String status;

}
