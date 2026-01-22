package ma.fsr.soa.consultationserviceapi.web;

import ma.fsr.soa.cabinetrepo.model.Consultation;
import ma.fsr.soa.consultationserviceapi.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/api/v1/consultations")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @GetMapping
    public List<Consultation> list() {
        return consultationService.listerTout();
    }

    @PostMapping
    public Consultation save(@RequestBody Consultation cons) {
        return consultationService.creerConsultation(cons);
    }
}