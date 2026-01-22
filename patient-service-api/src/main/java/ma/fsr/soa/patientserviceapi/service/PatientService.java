package ma.fsr.soa.patientserviceapi.service;

import ma.fsr.soa.cabinetrepo.model.Patient;
import ma.fsr.soa.cabinetrepo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Règle : Lister tous les patients
     */
    public List<Patient> listerTous() {
        return patientRepository.findAll();
    }

    /**
     * Règle : Obtenir par ID ou erreur spécifique
     */
    public Patient obtenirParId(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient introuvable : id = " + id));
    }

    /**
     * Règle : Créer avec validations métier
     */
    public Patient creerPatient(Patient p) {
        validerPatient(p);
        return patientRepository.save(p);
    }

    /**
     * Règle : Modifier un patient existant
     */
    public Patient modifierPatient(Long id, Patient p) {
        // On vérifie d'abord s'il existe
        Patient patientExistant = obtenirParId(id);

        // On valide les nouvelles données
        validerPatient(p);

        // Mise à jour des champs
        patientExistant.setNom(p.getNom());
        patientExistant.setPrenom(p.getPrenom());
        patientExistant.setTelephone(p.getTelephone());
        patientExistant.setEmail(p.getEmail());
        patientExistant.setDateNaissance(p.getDateNaissance());

        return patientRepository.save(patientExistant);
    }

    /**
     * Règle : Supprimer un patient
     */
    public void supprimerPatient(Long id) {
        Patient p = obtenirParId(id);
        patientRepository.delete(p);
    }

    /**
     * Méthode interne pour centraliser les règles de gestion (DRY)
     */
    private void validerPatient(Patient p) {
        if (p.getNom() == null || p.getNom().trim().isEmpty()) {
            throw new RuntimeException("Le nom du patient est obligatoire.");
        }
        if (p.getTelephone() == null || p.getTelephone().trim().isEmpty()) {
            throw new RuntimeException("Le téléphone du patient est obligatoire.");
        }
        if (p.getDateNaissance() != null && p.getDateNaissance().after(new Date())) {
            throw new RuntimeException("La date de naissance ne peut pas être future");
        }
    }
}
