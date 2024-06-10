package Team.project.service.StudentHistory;

import Team.project.dto.student.StudentHistoryDto;
import Team.project.repository.StudentHistory.StudentHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentHistoryServiceImpl implements StudentHistoryService{

    private final StudentHistoryRepository studentHistoryRepository;

    @Override
    public void updateEnrollments(StudentHistoryDto studentHistoryDto) {
        studentHistoryRepository.updateEnrollments(studentHistoryDto);
    }

}
