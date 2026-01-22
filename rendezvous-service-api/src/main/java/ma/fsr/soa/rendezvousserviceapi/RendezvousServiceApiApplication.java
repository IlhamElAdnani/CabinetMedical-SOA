package ma.fsr.soa.rendezvousserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.Bean; // Import à ajouter
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate; // Import à ajouter

@SpringBootApplication
@ComponentScan(basePackages = {"ma.fsr.soa.rendezvousserviceapi", "ma.fsr.soa.cabinetrepo"})
@EnableJpaRepositories(basePackages = "ma.fsr.soa.cabinetrepo.repository")
@EntityScan(basePackages = "ma.fsr.soa.cabinetrepo.model")
public class RendezvousServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RendezvousServiceApiApplication.class, args);
    }

    // Injecter RestTemplate dans vos Controllers ou Services
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}