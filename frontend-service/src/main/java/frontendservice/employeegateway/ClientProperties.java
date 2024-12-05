package frontendservice.employeegateway;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "employee.gateway")
@Data
public class ClientProperties {

    private String url;
}
