package ma.fsr.soa.rendezvousserviceapi.web;

import ma.fsr.soa.cabinetrepo.model.Patient;
import ma.fsr.soa.cabinetrepo.model.Medecin;
import ma.fsr.soa.cabinetrepo.model.RendezVous;
import ma.fsr.soa.rendezvousserviceapi.service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate; // Import important
import java.util.List;

@RestController
@RequestMapping("/internal/api/v1/rendezvous")
public class RendezVousController {

    @Autowired
    private RendezVousService rendezvousService;

    @Autowired
    private RestTemplate restTemplate; // Injection du Bean

    @GetMapping
    public List<RendezVous> list() {
        List<RendezVous> rdvs = rendezvousService.listerTout();
        rdvs.forEach(this::enrichirRendezVous);
        return rdvs;
    }

    @PostMapping
    public RendezVous create(@RequestBody RendezVous rdv) {
        RendezVous savedRdv = rendezvousService.creerRendezVous(rdv);
        enrichirRendezVous(savedRdv);
        return savedRdv;
    }
    private void enrichirRendezVous(RendezVous rdv) {
        try {
            if (rdv.getPatient() != null && rdv.getPatient().getId() != null) {
                String urlPatient = "http://localhost:8082/internal/api/v1/patients/" + rdv.getPatient().getId();
                Patient p = restTemplate.getForObject(urlPatient, Patient.class);
                rdv.setPatient(p);
            }
            if (rdv.getMedecin() != null && rdv.getMedecin().getId() != null) {
                String urlMedecin = "http://localhost:8083/internal/api/v1/medecins/" + rdv.getMedecin().getId();
                Medecin m = restTemplate.getForObject(urlMedecin, Medecin.class);
                rdv.setMedecin(m);
            }
        } catch (Exception e) {
            System.err.println("Erreur de communication SOA : " + e.getMessage());
        }
    }

    @PatchMapping("/{id}/statut")
    public RendezVous updateStatut(@PathVariable Long id, @RequestParam String statut) {
        RendezVous rdv = rendezvousService.modifierStatut(id, statut);
        enrichirRendezVous(rdv);
        return rdv;
    }
}