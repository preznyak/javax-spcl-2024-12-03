package careerservice.enrollment.gateway;

import careerservice.enrollment.model.EnrollCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class CourseGateway {

    public void sendEnrollCommandToCourseService(EnrollCommand enrollCommand){
        log.info("Sending enroll command to course service.");
    }
}
