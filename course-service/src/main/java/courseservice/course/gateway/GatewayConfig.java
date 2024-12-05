package courseservice.course.gateway;

import courseservice.course.dto.EnrollCommand;
import courseservice.course.dto.EnrollmentResult;
import courseservice.course.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration(proxyBeanMethods = false)
@AllArgsConstructor
public class GatewayConfig {

    private CourseService courseService;

    @Bean
    public Function<EnrollCommandRequest, EnrollResponse> enroll() {
        return request -> {
            EnrollmentResult result = courseService.enroll(new EnrollCommand(request.courseId(), request.employeeId()));
            EnrollResponse.EnrollResult enrollResult =
                    switch (result.getSuccess()) {
                        case SUCCESSFUL -> EnrollResponse.EnrollResult.OK;
                        case UNSUCCESSFUL -> EnrollResponse.EnrollResult.FULL;
                    };
            return new EnrollResponse(request.employeeId(), request.courseId(), enrollResult);
        };
    }
}
