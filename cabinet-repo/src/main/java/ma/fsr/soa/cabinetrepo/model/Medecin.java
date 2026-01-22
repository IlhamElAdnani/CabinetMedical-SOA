package ma.fsr.soa.cabinetrepo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String specialite;

    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL)
    @JsonIgnore // Important pour éviter la boucle infinie
    private List<RendezVous> rendezvous;
}