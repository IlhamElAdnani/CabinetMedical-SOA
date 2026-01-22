package ma.fsr.soa.medecinserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"ma.fsr.soa.medecinserviceapi", "ma.fsr.soa.cabinetrepo"})
@EnableJpaRepositories(basePackages = "ma.fsr.soa.cabinetrepo.repository")
@EntityScan(basePackages = "ma.fsr.soa.cabinetrepo.model")
public class MedecinServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedecinServiceApiApplication.class, args);
    }

}
