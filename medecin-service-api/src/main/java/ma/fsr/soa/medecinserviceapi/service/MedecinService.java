package ma.fsr.soa.medecinserviceapi.service;

import ma.fsr.soa.cabinetrepo.model.Medecin;
import ma.fsr.soa.cabinetrepo.repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedecinService {

    @Autowired
    private MedecinRepository medecinRepository;

    public List<Medecin> listerTous() {
        return medecinRepository.findAll();
    }

    public Medecin obtenirParId(Long id) {
        return medecinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médecin introuvable : id = " + id));
    }

    public Medecin creerMedecin(Medecin m) {
        validerMedecin(m);
        return medecinRepository.save(m);
    }

    public Medecin modifierMedecin(Long id, Medecin m) {
        Medecin existant = obtenirParId(id);
        validerMedecin(m);

        existant.setNom(m.getNom());
        existant.setEmail(m.getEmail());
        existant.setSpecialite(m.getSpecialite());
        // Ajoutez d'autres champs si nécessaire (ex: prenom)

        return medecinRepository.save(existant);
    }

    public void supprimer(Long id) {
        Medecin m = obtenirParId(id);
        medecinRepository.delete(m);
    }

    private void validerMedecin(Medecin m) {
        if (m.getNom() == null || m.getNom().trim().isEmpty())
            throw new RuntimeException("Le nom du médecin est obligatoire.");

        if (m.getEmail() == null || m.getEmail().trim().isEmpty())
            throw new RuntimeException("L'email du médecin est obligatoire.");

        if (!m.getEmail().contains("@"))
            throw new RuntimeException("Email du médecin invalide.");

        if (m.getSpecialite() == null || m.getSpecialite().trim().isEmpty())
            throw new RuntimeException("La spécialité du médecin est obligatoire.");
    }
}