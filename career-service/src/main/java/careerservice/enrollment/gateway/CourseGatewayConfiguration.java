package careerservice.enrollment.gateway;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration(proxyBeanMethods = false)
@AllArgsConstructor
public class CourseGatewayConfiguration {

    private ApplicationEventPublisher applicationEventPublisher;

    @Bean
    public Consumer<EnrollResponse> enrollResponseConsumer() {
        return applicationEventPublisher::publishEvent;
    }
}
