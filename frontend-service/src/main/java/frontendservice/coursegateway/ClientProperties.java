package frontendservice.coursegateway;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "course.gateway")
@Data
public class ClientProperties {

    private String url;
}
