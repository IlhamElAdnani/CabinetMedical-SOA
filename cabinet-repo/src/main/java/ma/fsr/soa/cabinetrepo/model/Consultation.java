package ma.fsr.soa.cabinetrepo.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Consultation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateConsultation;
    private String rapport;

    // Relation 1-à-1 : Une consultation pour un rendez-vous spécifique
    @OneToOne
    private RendezVous rendezVous;
}