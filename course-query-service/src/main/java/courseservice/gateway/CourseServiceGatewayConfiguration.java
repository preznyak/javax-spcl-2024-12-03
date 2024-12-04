package courseservice.gateway;

import courseservice.course.CourseHasBeenCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration(proxyBeanMethods = false)
@AllArgsConstructor
@Slf4j
public class CourseServiceGatewayConfiguration {

    private ApplicationEventPublisher eventPublisher;

    @Bean
    public Consumer<CourseHasBeenCreatedEvent> handleCourseCreated() {
        return event -> {
            log.info("handleCourseCreated: {}", event);
            eventPublisher.publishEvent(event);
        };
    }
}
