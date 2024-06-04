package Team.project.service.info;

import Team.project.dto.info.InfoDto;
import jakarta.servlet.http.HttpSession;

public interface InfoService {
    InfoDto findStudent(Long id, HttpSession session);

    InfoDto studentUpdate(Long id, InfoDto infoDto);

    InfoDto findProfessor(Long id, HttpSession session);

    InfoDto professorUpdate(Long id, InfoDto infoDto);

}
