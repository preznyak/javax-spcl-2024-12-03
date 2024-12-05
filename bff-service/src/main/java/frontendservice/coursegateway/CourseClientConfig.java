package frontendservice.coursegateway;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@EnableConfigurationProperties(CourseGatewayProperties.class)
public class CourseClientConfig {

    @Bean
    public CourseClient courseClient(RestClient.Builder builder, CourseGatewayProperties properties) {
        var client = builder.baseUrl(properties.getUrl()).build();
        var factory = HttpServiceProxyFactory.builderFor(
                RestClientAdapter.create(client)).build();
        return factory.createClient(CourseClient.class);
    }
}
