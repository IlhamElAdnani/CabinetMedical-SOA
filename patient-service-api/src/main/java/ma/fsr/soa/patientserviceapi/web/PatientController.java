package ma.fsr.soa.patientserviceapi.web;

import ma.fsr.soa.cabinetrepo.model.Patient;
import ma.fsr.soa.patientserviceapi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/api/v1/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;


    // 1. Lister tous les patients : GET /internal/api/v1/patients
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.listerTous();
    }

    // 2. Obtenir un patient par ID : GET /internal/api/v1/patients/{id}
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.obtenirParId(id);
    }

    // 3. Créer un patient : POST /internal/api/v1/patients
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.creerPatient(patient);
    }

    // 4. Modifier un patient : PUT /internal/api/v1/patients/{id}
    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        return patientService.modifierPatient(id, patient);
    }

    // 5. Supprimer un patient : DELETE /internal/api/v1/patients/{id}
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.supprimerPatient(id);
    }
}