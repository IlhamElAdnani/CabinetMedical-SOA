package ma.fsr.soa.consultationserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"ma.fsr.soa.consultationserviceapi", "ma.fsr.soa.cabinetrepo"})
@EnableJpaRepositories(basePackages = "ma.fsr.soa.cabinetrepo.repository")
@EntityScan(basePackages = "ma.fsr.soa.cabinetrepo.model")


public class ConsultationServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsultationServiceApiApplication.class, args);
    }

}
