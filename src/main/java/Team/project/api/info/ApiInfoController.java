package Team.project.api.info;

import Team.project.dto.info.InfoDto;
import Team.project.service.info.InfoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiInfoController {

    private final InfoService infoService;

    /* 학생 정보 반환 */
    @GetMapping("/student/info/{id}")
    public ResponseEntity<?> info1(@PathVariable Long id, HttpSession session){

        InfoDto infoDto = infoService.findStudent(id, session);

        return ResponseEntity.ok(infoDto);
    }

    /* 교수 정보 반환 */
    @GetMapping("/professor/info/{id}")
    public ResponseEntity<?> info2(@PathVariable Long id, HttpSession session){

        InfoDto infoDto = infoService.findProfessor(id, session);

        return ResponseEntity.ok(infoDto);
    }

    /* 학생 정보 수정 */
    @PatchMapping("/student/info/{id}")
    public ResponseEntity<?> update1(@PathVariable Long id, @RequestBody @Valid InfoDto infoDto){

            InfoDto updateDto = infoService.studentUpdate(id, infoDto);

            return ResponseEntity.ok(updateDto);

    }

    /* 교수 정보 수정 */
    @PatchMapping("/professor/info/{id}")
    public ResponseEntity<?> update2(@PathVariable Long id, @RequestBody @Valid InfoDto infoDto){

            InfoDto updateDto = infoService.professorUpdate(id, infoDto);

            return ResponseEntity.ok(updateDto);
    }

}
