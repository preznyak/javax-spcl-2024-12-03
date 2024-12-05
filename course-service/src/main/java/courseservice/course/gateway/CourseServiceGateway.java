package courseservice.course.gateway;

import courseservice.course.CourseHasBeenCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;

@Gateway
@AllArgsConstructor
public class CourseServiceGateway {

    private StreamBridge streamBridge;

    @EventListener
    public void handleEvent(CourseHasBeenCreatedEvent event) {
        streamBridge.send("course-events-topic", event);
    }
}
