package Team.project.dto.info;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoDto {

    private Long id;
    private Long hakbun;

    @NotBlank(message = "name 비어있으면 안됩니다.")
    private String name;

    @NotBlank(message = "password 비어있으면 안됩니다.")
    private String password;

    private String major;

    @NotBlank(message = "city 비어있으면 안됩니다.")
    private String city;

    @NotBlank(message = "street 비어있으면 안됩니다.")
    private String street;
    private int years;
    private int semester;
    private String status;

    public InfoDto(Long id, Long hakbun, String name, String password, String major, String city, String street, int years, int semester) {
        this.id = id;
        this.hakbun = hakbun;
        this.name = name;
        this.password = password;
        this.major = major;
        this.city = city;
        this.street = street;
        this.years = years;
        this.semester = semester;
    }

    public InfoDto(Long id, Long loginId, String name, String password, String major, String city, String street) {
        this.id = id;
        this.hakbun = loginId;
        this.name = name;
        this.password = password;
        this.major = major;
        this.city = city;
        this.street = street;
    }


}
