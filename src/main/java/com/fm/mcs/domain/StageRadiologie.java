package com.fm.mcs.domain;

import com.fm.mcs.domain.enumeration.AnneeEtude;
import com.fm.mcs.domain.enumeration.Hopital;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A StageRadiologie.
 */
@Entity
@Table(name = "stage_radiologie")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StageRadiologie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "annee_etude", nullable = false)
    private AnneeEtude anneeEtude;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "hopital", nullable = false)
    private Hopital hopital;

    @Column(name = "chef_service")
    private String chefService;

    @Column(name = "semestre")
    private String semestre;

    @Column(name = "groupe")
    private String groupe;

    @Column(name = "objectif_1")
    private String objectif1;

    @Column(name = "objectif_1_enseigne")
    private String objectif1Enseigne;

    @Column(name = "note_objectif_1")
    private Integer noteObjectif1;

    @ManyToOne
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StageRadiologie id(Long id) {
        this.id = id;
        return this;
    }

    public AnneeEtude getAnneeEtude() {
        return this.anneeEtude;
    }

    public StageRadiologie anneeEtude(AnneeEtude anneeEtude) {
        this.anneeEtude = anneeEtude;
        return this;
    }

    public void setAnneeEtude(AnneeEtude anneeEtude) {
        this.anneeEtude = anneeEtude;
    }

    public LocalDate getDateDebut() {
        return this.dateDebut;
    }

    public StageRadiologie dateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return this.dateFin;
    }

    public StageRadiologie dateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Hopital getHopital() {
        return this.hopital;
    }

    public StageRadiologie hopital(Hopital hopital) {
        this.hopital = hopital;
        return this;
    }

    public void setHopital(Hopital hopital) {
        this.hopital = hopital;
    }

    public String getChefService() {
        return this.chefService;
    }

    public StageRadiologie chefService(String chefService) {
        this.chefService = chefService;
        return this;
    }

    public void setChefService(String chefService) {
        this.chefService = chefService;
    }

    public String getSemestre() {
        return this.semestre;
    }

    public StageRadiologie semestre(String semestre) {
        this.semestre = semestre;
        return this;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getGroupe() {
        return this.groupe;
    }

    public StageRadiologie groupe(String groupe) {
        this.groupe = groupe;
        return this;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getObjectif1() {
        return this.objectif1;
    }

    public StageRadiologie objectif1(String objectif1) {
        this.objectif1 = objectif1;
        return this;
    }

    public void setObjectif1(String objectif1) {
        this.objectif1 = objectif1;
    }

    public String getObjectif1Enseigne() {
        return this.objectif1Enseigne;
    }

    public StageRadiologie objectif1Enseigne(String objectif1Enseigne) {
        this.objectif1Enseigne = objectif1Enseigne;
        return this;
    }

    public void setObjectif1Enseigne(String objectif1Enseigne) {
        this.objectif1Enseigne = objectif1Enseigne;
    }

    public Integer getNoteObjectif1() {
        return this.noteObjectif1;
    }

    public StageRadiologie noteObjectif1(Integer noteObjectif1) {
        this.noteObjectif1 = noteObjectif1;
        return this;
    }

    public void setNoteObjectif1(Integer noteObjectif1) {
        this.noteObjectif1 = noteObjectif1;
    }

    public User getUser() {
        return this.user;
    }

    public StageRadiologie user(User user) {
        this.setUser(user);
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StageRadiologie)) {
            return false;
        }
        return id != null && id.equals(((StageRadiologie) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StageRadiologie{" +
            "id=" + getId() +
            ", anneeEtude='" + getAnneeEtude() + "'" +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", hopital='" + getHopital() + "'" +
            ", chefService='" + getChefService() + "'" +
            ", semestre='" + getSemestre() + "'" +
            ", groupe='" + getGroupe() + "'" +
            ", objectif1='" + getObjectif1() + "'" +
            ", objectif1Enseigne='" + getObjectif1Enseigne() + "'" +
            ", noteObjectif1=" + getNoteObjectif1() +
            "}";
    }
}
