package frontendservice.employeegateway;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@EnableConfigurationProperties(ClientProperties.class)
public class EmployeeServiceClientConfiguration {

    @Bean
    public EmployeeServiceClient employeesClient(RestClient.Builder builder, ClientProperties clientProperties) {
        var client = builder.baseUrl(clientProperties.getUrl())
                .build();
        var factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(client)).build();
        return factory.createClient(EmployeeServiceClient.class);
    }

}
