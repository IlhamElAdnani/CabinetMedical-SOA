package ma.fsr.soa.cabinetrepo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String genre;
    private String telephone;
    private Date dateNaissance;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonIgnore // Important pour éviter la boucle infinie
    private List<RendezVous> rendezvous;
}