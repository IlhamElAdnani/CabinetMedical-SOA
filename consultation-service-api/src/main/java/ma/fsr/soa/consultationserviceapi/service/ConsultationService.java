package ma.fsr.soa.consultationserviceapi.service;

import ma.fsr.soa.cabinetrepo.model.Consultation;
import ma.fsr.soa.cabinetrepo.model.RendezVous;
import ma.fsr.soa.cabinetrepo.repository.ConsultationRepository;
import ma.fsr.soa.cabinetrepo.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private RendezVousRepository rendezvousRepository;

    public List<Consultation> listerTout() {
        return consultationRepository.findAll();
    }

    public Consultation creerConsultation(Consultation cons) {

        if (cons.getRendezVous() == null || cons.getRendezVous().getId() == null) {
            throw new RuntimeException("Rendez-vous introuvable.");
        }

        RendezVous rdv = rendezvousRepository.findById(cons.getRendezVous().getId())
                .orElseThrow(() -> new RuntimeException("Rendez-vous introuvable."));

        if (cons.getDateConsultation() == null) {
            throw new RuntimeException("La date de consultation est obligatoire.");
        }


        if (cons.getDateConsultation().before(rdv.getDateRDV())) {
            throw new RuntimeException("Date de consultation invalide.");
        }

        if (cons.getRapport() == null || cons.getRapport().trim().length() < 10) {
            throw new RuntimeException("Rapport de consultation insuffisant.");
        }

        return consultationRepository.save(cons);
    }
}