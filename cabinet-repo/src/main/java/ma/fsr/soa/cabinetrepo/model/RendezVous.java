package ma.fsr.soa.cabinetrepo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateRDV;

    @Enumerated(EnumType.STRING)
    private StatutRDV statut;

    @ManyToOne
    @JoinColumn(name = "patient_id" , foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medecin_id" , foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Medecin medecin;

    @OneToOne(mappedBy = "rendezVous")
    @JsonIgnore
    private Consultation consultation;
}