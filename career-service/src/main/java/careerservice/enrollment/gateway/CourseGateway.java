package careerservice.enrollment.gateway;

import careerservice.enrollment.model.EnrollCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class CourseGateway {

    private final StreamBridge streamBridge;

    public void sendEnrollCommandToCourseService(EnrollCommand enrollCommand) {
        log.info("Sending enroll command to course service");
        streamBridge.send("course-command-topic", enrollCommand);
    }
}
