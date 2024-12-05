package careerservice.enrollment.saga;

import careerservice.enrollment.gateway.CourseGateway;
import careerservice.enrollment.gateway.EnrollResponse;
import careerservice.enrollment.model.EnrollCommand;
import careerservice.enrollment.service.EnrollmentService;
import careerservice.enrollment.view.EnrollmentView;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EnrollSaga {

    private final CourseGateway courseGateway;

    private EnrollmentService enrollmentService;

    public EnrollmentView enroll(EnrollCommand enrollCommand) {
        EnrollmentView view = enrollmentService.enrollToCourse(enrollCommand);
        courseGateway.sendEnrollCommandToCourseService(enrollCommand);
        return view;
    }

    @EventListener
    public void handleEnrollResponse(EnrollResponse enrollResponse) {
        switch (enrollResponse.enrollResult()) {
            case OK -> enrollmentService.complete(enrollResponse.employeeId(), enrollResponse.courseId());
            case FULL -> enrollmentService.fail(enrollResponse.employeeId(), enrollResponse.courseId());
        }
    }
}
