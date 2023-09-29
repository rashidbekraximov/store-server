package uz.cluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import uz.cluster.configuration.OpenApiProperties;


@EnableConfigurationProperties({
        OpenApiProperties.class,
})

@EnableJpaRepositories(value = {"uz.cluster.*"})
@ComponentScan(value = {"uz.cluster.*"})
@SpringBootApplication()
public class ClinicApplication  {
    public static void main(String[] args) {
        SpringApplication.run(ClinicApplication.class, args);
    }
}
