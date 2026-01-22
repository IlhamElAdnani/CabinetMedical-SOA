package ma.fsr.soa.rendezvousserviceapi.service;

import ma.fsr.soa.cabinetrepo.model.RendezVous;
import ma.fsr.soa.cabinetrepo.model.StatutRDV;
import ma.fsr.soa.cabinetrepo.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RendezVousService {

    @Autowired
    private RendezVousRepository rendezvousRepository;

    // Nommé pour correspondre à rendezvousService.listerTout()
    public List<RendezVous> listerTout() {
        return rendezvousRepository.findAll();
    }

    // Nommé pour correspondre à rendezvousService.creerRendezVous(rdv)
    public RendezVous creerRendezVous(RendezVous rdv) {
        return rendezvousRepository.save(rdv);
    }

    // Nommé pour correspondre à rendezvousService.modifierStatut(id, statut)
    public RendezVous modifierStatut(Long id, String statut) {
        RendezVous rdv = rendezvousRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendez-vous introuvable"));

        // Conversion de la String en Enum StatutRDV
        rdv.setStatut(StatutRDV.valueOf(statut.toUpperCase()));

        return rendezvousRepository.save(rdv);
    }
}