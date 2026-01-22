package ma.fsr.soa.patientserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages = {"ma.fsr.soa.patientserviceapi", "ma.fsr.soa.cabinetrepo"})
@EnableJpaRepositories(basePackages = "ma.fsr.soa.cabinetrepo.repository")
@EntityScan(basePackages = "ma.fsr.soa.cabinetrepo.model")
public class PatientServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientServiceApiApplication.class, args);
    }

}
