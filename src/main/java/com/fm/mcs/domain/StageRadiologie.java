package com.fm.mcs.domain;

import com.fm.mcs.domain.enumeration.AnneeEtude;
import com.fm.mcs.domain.enumeration.EvaluationEtudiant;
import com.fm.mcs.domain.enumeration.Hopital;
import com.fm.mcs.domain.enumeration.NoteEncadrantReferent;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "evaluation_objectif_1_etudiant")
    private EvaluationEtudiant evaluationObjectif1Etudiant;

    @Enumerated(EnumType.STRING)
    @Column(name = "note_objectif_1_encadrant_referent")
    private NoteEncadrantReferent noteObjectif1EncadrantReferent;

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

    public EvaluationEtudiant getEvaluationObjectif1Etudiant() {
        return this.evaluationObjectif1Etudiant;
    }

    public StageRadiologie evaluationObjectif1Etudiant(EvaluationEtudiant evaluationObjectif1Etudiant) {
        this.evaluationObjectif1Etudiant = evaluationObjectif1Etudiant;
        return this;
    }

    public void setEvaluationObjectif1Etudiant(EvaluationEtudiant evaluationObjectif1Etudiant) {
        this.evaluationObjectif1Etudiant = evaluationObjectif1Etudiant;
    }

    public NoteEncadrantReferent getNoteObjectif1EncadrantReferent() {
        return this.noteObjectif1EncadrantReferent;
    }

    public StageRadiologie noteObjectif1EncadrantReferent(NoteEncadrantReferent noteObjectif1EncadrantReferent) {
        this.noteObjectif1EncadrantReferent = noteObjectif1EncadrantReferent;
        return this;
    }

    public void setNoteObjectif1EncadrantReferent(NoteEncadrantReferent noteObjectif1EncadrantReferent) {
        this.noteObjectif1EncadrantReferent = noteObjectif1EncadrantReferent;
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
            ", evaluationObjectif1Etudiant='" + getEvaluationObjectif1Etudiant() + "'" +
            ", noteObjectif1EncadrantReferent='" + getNoteObjectif1EncadrantReferent() + "'" +
            "}";
    }
}
