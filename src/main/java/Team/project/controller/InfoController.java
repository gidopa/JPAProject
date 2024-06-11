package Team.project.controller;

import Team.project.dto.info.InfoDto;
import Team.project.exception.InfoCustomException;
import Team.project.exception.SessionNotFoundException;
import Team.project.service.info.InfoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class InfoController {

    private final InfoService infoService;

    /* 학생 정보 수정 뷰 */
    @GetMapping("/web/student/info/{id}")
    public String info(@PathVariable Long id, Model model, HttpSession session){
        log.info("학생 정보 조회 시도 중 - uri.id:{}",id);

        InfoDto infoDto = infoService.findStudent(id, session);

        model.addAttribute("infoDto", infoDto);

        log.info("학생 정보 조회 성공 - InfoDto: {}", infoDto);
        return "info/studentUpdate";
    }

    /* 교수 정보 수정 뷰 */
    @GetMapping("/web/professor/info/{id}")
    public String info2(@PathVariable Long id, Model model, HttpSession session){
        log.info("교수 정보 조회 시도 중 - uri.id:{}", id);

        InfoDto infoDto = infoService.findProfessor(id, session);

        model.addAttribute("infoDto", infoDto);

        log.info("조회 성공 - InfoDto: {}", infoDto);
        return "info/professorUpdate";
    }

    /* 학생 정보 수정 */
    @PostMapping("/web/student/info/{id}")
    public String update1(@PathVariable Long id, @ModelAttribute @Valid InfoDto infoDto, BindingResult bindingResult, Model model){
        log.info("학생 정보 수정 시도 중 - infoDto: {}", infoDto);

        if(bindingResult.hasErrors()){
            model.addAttribute("infoDto", infoDto);
            return "info/studentUpdate";
        }

        infoService.studentUpdate(id, infoDto);

        log.info("학생 정보 수정 성공 - infoDto: {}", infoDto);
        return "redirect:/";
    }

    /* 교수 정보 수정 */
    @PostMapping("/web/professor/info/{id}")
    public String update2(@PathVariable Long id, @ModelAttribute @Valid InfoDto infoDto, BindingResult bindingResult, Model model){
        log.info("교수 정보 변경 시도 중 - infoDto: {}", infoDto);

        if(bindingResult.hasErrors()){
            model.addAttribute("infoDto", infoDto);
            return "info/professorUpdate";
        }

        infoService.professorUpdate(id, infoDto);

        log.info("교수 정보 수정 성공 - infoDto: {}", infoDto);
        return "redirect:/";
    }


    /* 예외 처리 */
    @ExceptionHandler(InfoCustomException.class)
    public String handlerException(InfoCustomException ex, Model model){
        model.addAttribute("errorMessage", ex.getMessage());
        return "common/error";
    }

    @ExceptionHandler(SessionNotFoundException.class)
    public String handlerException(SessionNotFoundException ex, Model model){

        model.addAttribute("errorMessage", ex.getMessage());

        return "common/error";
    }

}
