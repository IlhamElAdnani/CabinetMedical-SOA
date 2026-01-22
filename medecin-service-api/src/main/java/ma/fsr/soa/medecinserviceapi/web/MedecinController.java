package ma.fsr.soa.medecinserviceapi.web;

import ma.fsr.soa.cabinetrepo.model.Medecin;
import ma.fsr.soa.medecinserviceapi.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/internal/api/v1/medecins")
public class MedecinController {

    @Autowired
    private MedecinService medecinService;

    @GetMapping
    public List<Medecin> getAll() { return medecinService.listerTous(); }

    @GetMapping("/{id}")
    public Medecin getOne(@PathVariable Long id) { return medecinService.obtenirParId(id); }

    @PostMapping
    public Medecin create(@RequestBody Medecin m) { return medecinService.creerMedecin(m); }

    @PutMapping("/{id}")
    public Medecin update(@PathVariable Long id, @RequestBody Medecin m) {
        return medecinService.modifierMedecin(id, m);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { medecinService.supprimer(id); }
}