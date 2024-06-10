package Team.project.controller;

import Team.project.service.schoolRecord.SchoolRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SchoolRecordController {

    private final SchoolRecordService schoolRecordService;
}
