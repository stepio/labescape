package co.tide.labescape.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Separate class with Swagger2-specific configuration.
 * Allows automatic generating of REST API specification.
 *
 * @author istepanov
 * @see <a href="http://springfox.github.io/springfox/docs/current/#dependencies">Springfox-swagger2 with Spring MVC and Spring Boot</a>
 */
@Configuration
public class Swagger2Config {

    @Value("${swagger2.enabled:true}")
    protected boolean swagger2Enabled;

    @Bean
    public Docket swaggerApiDescription() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(this.swagger2Enabled)
                .select()
                .paths(PathSelectors.regex("/v1/.*"))
                .build();
    }
}
