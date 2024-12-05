package frontendservice.coursegateway;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@EnableConfigurationProperties(ClientProperties.class)
@Configuration
public class CourseServiceClientConfiguration {

    @Bean
    public CourseServiceClient coursesClient(RestClient.Builder builder, ClientProperties clientProperties) {
        var client = builder.baseUrl(clientProperties.getUrl()).build();
        var factory = HttpServiceProxyFactory.builderFor(
                RestClientAdapter.create(client)).build();
        return factory.createClient(CourseServiceClient.class);
    }
}
