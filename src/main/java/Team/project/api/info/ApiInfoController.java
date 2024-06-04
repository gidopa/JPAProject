package Team.project.api.info;

import Team.project.dto.info.InfoDto;
import Team.project.exception.InfoCustomException;
import Team.project.service.info.InfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiInfoController {

    private final InfoService infoService;

    @PatchMapping("/student/info/{id}")
    public ResponseEntity<?> update1(@PathVariable Long id, @RequestBody @Valid InfoDto infoDto){

        try{
            InfoDto updateDto = infoService.studentUpdate(id, infoDto);

            return ResponseEntity.ok(updateDto);

        }catch (InfoCustomException e){
            return handlerException(e);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러가 발생하였습니다.");
        }

    }

    @PatchMapping("/professor/info/{id}")
    public ResponseEntity<?> update2(@PathVariable Long id, @RequestBody @Valid InfoDto infoDto){

        try{
            InfoDto updateDto = infoService.professorUpdate(id, infoDto);

            return ResponseEntity.ok(updateDto);
        }catch (InfoCustomException e){
            return handlerException(e);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러가 발생하였습니다.");
        }
    }

    @ExceptionHandler(InfoCustomException.class)
    public ResponseEntity<String> handlerException(InfoCustomException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
