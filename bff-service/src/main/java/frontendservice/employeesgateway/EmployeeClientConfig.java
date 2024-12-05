package frontendservice.employeesgateway;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@EnableConfigurationProperties(EmployeeGatewayProperties.class)
public class EmployeeClientConfig {

    @Bean
    public EmployeeClient employeeClient(RestClient.Builder builder, EmployeeGatewayProperties properties) {
        var client = builder.baseUrl(properties.getUrl()).build();
        var factory = HttpServiceProxyFactory.builderFor(
                RestClientAdapter.create(client)).build();
        return factory.createClient(EmployeeClient.class);
    }
}
