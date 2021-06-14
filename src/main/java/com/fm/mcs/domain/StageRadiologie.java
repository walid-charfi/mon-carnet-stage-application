package com.fm.mcs.domain;

import com.fm.mcs.domain.enumeration.AnneeEtude;
import com.fm.mcs.domain.enumeration.Hopital;
import java.io.Serializable;
import java.time.Instant;
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
    private Instant dateDebut;

    @Column(name = "date_fin")
    private Instant dateFin;

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

    @Column(name = "specialite")
    private String specialite;

    @Column(name = "objectif_1")
    private String objectif1;

    @Column(name = "objectif_1_enseigne")
    private String objectif1Enseigne;

    @Column(name = "is_objectif_1_evalue")
    private Boolean isObjectif1Evalue;

    @Column(name = "note_objectif_1")
    private Integer noteObjectif1;

    @Column(name = "evaluation_objectif_par_objectif")
    private Integer evaluationObjectifParObjectif;

    @Column(name = "evaluation_redaction_observation")
    private Integer evaluationRedactionObservation;

    @Column(name = "evaluation_elaboration_resume")
    private Integer evaluationElaborationResume;

    @Column(name = "evaluation_attitude_assiduite")
    private Integer evaluationAttitudeAssiduite;

    @Column(name = "evaludation_validation_garde")
    private Integer evaludationValidationGarde;

    @Column(name = "evaluation_periodique")
    private Integer evaluationPeriodique;

    @Column(name = "e_cosm")
    private Integer eCOSM;

    @Column(name = "total")
    private Integer total;

    @Column(name = "is_absence_sup_10_pc")
    private Boolean isAbsenceSup10pc;

    @Column(name = "is_valide")
    private Boolean isValide;

    @Column(name = "duree_stage_compl_en_semaine")
    private Integer dureeStageComplEnSemaine;

    @Column(name = "date_debut_stage_compl")
    private Instant dateDebutStageCompl;

    @Column(name = "date_fin_stage_compl")
    private Instant dateFinStageCompl;

    @Column(name = "is_stage_compl_valide")
    private Boolean isStageComplValide;

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

    public Instant getDateDebut() {
        return this.dateDebut;
    }

    public StageRadiologie dateDebut(Instant dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(Instant dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Instant getDateFin() {
        return this.dateFin;
    }

    public StageRadiologie dateFin(Instant dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(Instant dateFin) {
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

    public String getSpecialite() {
        return this.specialite;
    }

    public StageRadiologie specialite(String specialite) {
        this.specialite = specialite;
        return this;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
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

    public Boolean getIsObjectif1Evalue() {
        return this.isObjectif1Evalue;
    }

    public StageRadiologie isObjectif1Evalue(Boolean isObjectif1Evalue) {
        this.isObjectif1Evalue = isObjectif1Evalue;
        return this;
    }

    public void setIsObjectif1Evalue(Boolean isObjectif1Evalue) {
        this.isObjectif1Evalue = isObjectif1Evalue;
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

    public Integer getEvaluationObjectifParObjectif() {
        return this.evaluationObjectifParObjectif;
    }

    public StageRadiologie evaluationObjectifParObjectif(Integer evaluationObjectifParObjectif) {
        this.evaluationObjectifParObjectif = evaluationObjectifParObjectif;
        return this;
    }

    public void setEvaluationObjectifParObjectif(Integer evaluationObjectifParObjectif) {
        this.evaluationObjectifParObjectif = evaluationObjectifParObjectif;
    }

    public Integer getEvaluationRedactionObservation() {
        return this.evaluationRedactionObservation;
    }

    public StageRadiologie evaluationRedactionObservation(Integer evaluationRedactionObservation) {
        this.evaluationRedactionObservation = evaluationRedactionObservation;
        return this;
    }

    public void setEvaluationRedactionObservation(Integer evaluationRedactionObservation) {
        this.evaluationRedactionObservation = evaluationRedactionObservation;
    }

    public Integer getEvaluationElaborationResume() {
        return this.evaluationElaborationResume;
    }

    public StageRadiologie evaluationElaborationResume(Integer evaluationElaborationResume) {
        this.evaluationElaborationResume = evaluationElaborationResume;
        return this;
    }

    public void setEvaluationElaborationResume(Integer evaluationElaborationResume) {
        this.evaluationElaborationResume = evaluationElaborationResume;
    }

    public Integer getEvaluationAttitudeAssiduite() {
        return this.evaluationAttitudeAssiduite;
    }

    public StageRadiologie evaluationAttitudeAssiduite(Integer evaluationAttitudeAssiduite) {
        this.evaluationAttitudeAssiduite = evaluationAttitudeAssiduite;
        return this;
    }

    public void setEvaluationAttitudeAssiduite(Integer evaluationAttitudeAssiduite) {
        this.evaluationAttitudeAssiduite = evaluationAttitudeAssiduite;
    }

    public Integer getEvaludationValidationGarde() {
        return this.evaludationValidationGarde;
    }

    public StageRadiologie evaludationValidationGarde(Integer evaludationValidationGarde) {
        this.evaludationValidationGarde = evaludationValidationGarde;
        return this;
    }

    public void setEvaludationValidationGarde(Integer evaludationValidationGarde) {
        this.evaludationValidationGarde = evaludationValidationGarde;
    }

    public Integer getEvaluationPeriodique() {
        return this.evaluationPeriodique;
    }

    public StageRadiologie evaluationPeriodique(Integer evaluationPeriodique) {
        this.evaluationPeriodique = evaluationPeriodique;
        return this;
    }

    public void setEvaluationPeriodique(Integer evaluationPeriodique) {
        this.evaluationPeriodique = evaluationPeriodique;
    }

    public Integer geteCOSM() {
        return this.eCOSM;
    }

    public StageRadiologie eCOSM(Integer eCOSM) {
        this.eCOSM = eCOSM;
        return this;
    }

    public void seteCOSM(Integer eCOSM) {
        this.eCOSM = eCOSM;
    }

    public Integer getTotal() {
        return this.total;
    }

    public StageRadiologie total(Integer total) {
        this.total = total;
        return this;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Boolean getIsAbsenceSup10pc() {
        return this.isAbsenceSup10pc;
    }

    public StageRadiologie isAbsenceSup10pc(Boolean isAbsenceSup10pc) {
        this.isAbsenceSup10pc = isAbsenceSup10pc;
        return this;
    }

    public void setIsAbsenceSup10pc(Boolean isAbsenceSup10pc) {
        this.isAbsenceSup10pc = isAbsenceSup10pc;
    }

    public Boolean getIsValide() {
        return this.isValide;
    }

    public StageRadiologie isValide(Boolean isValide) {
        this.isValide = isValide;
        return this;
    }

    public void setIsValide(Boolean isValide) {
        this.isValide = isValide;
    }

    public Integer getDureeStageComplEnSemaine() {
        return this.dureeStageComplEnSemaine;
    }

    public StageRadiologie dureeStageComplEnSemaine(Integer dureeStageComplEnSemaine) {
        this.dureeStageComplEnSemaine = dureeStageComplEnSemaine;
        return this;
    }

    public void setDureeStageComplEnSemaine(Integer dureeStageComplEnSemaine) {
        this.dureeStageComplEnSemaine = dureeStageComplEnSemaine;
    }

    public Instant getDateDebutStageCompl() {
        return this.dateDebutStageCompl;
    }

    public StageRadiologie dateDebutStageCompl(Instant dateDebutStageCompl) {
        this.dateDebutStageCompl = dateDebutStageCompl;
        return this;
    }

    public void setDateDebutStageCompl(Instant dateDebutStageCompl) {
        this.dateDebutStageCompl = dateDebutStageCompl;
    }

    public Instant getDateFinStageCompl() {
        return this.dateFinStageCompl;
    }

    public StageRadiologie dateFinStageCompl(Instant dateFinStageCompl) {
        this.dateFinStageCompl = dateFinStageCompl;
        return this;
    }

    public void setDateFinStageCompl(Instant dateFinStageCompl) {
        this.dateFinStageCompl = dateFinStageCompl;
    }

    public Boolean getIsStageComplValide() {
        return this.isStageComplValide;
    }

    public StageRadiologie isStageComplValide(Boolean isStageComplValide) {
        this.isStageComplValide = isStageComplValide;
        return this;
    }

    public void setIsStageComplValide(Boolean isStageComplValide) {
        this.isStageComplValide = isStageComplValide;
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
            ", specialite='" + getSpecialite() + "'" +
            ", objectif1='" + getObjectif1() + "'" +
            ", objectif1Enseigne='" + getObjectif1Enseigne() + "'" +
            ", isObjectif1Evalue='" + getIsObjectif1Evalue() + "'" +
            ", noteObjectif1=" + getNoteObjectif1() +
            ", evaluationObjectifParObjectif=" + getEvaluationObjectifParObjectif() +
            ", evaluationRedactionObservation=" + getEvaluationRedactionObservation() +
            ", evaluationElaborationResume=" + getEvaluationElaborationResume() +
            ", evaluationAttitudeAssiduite=" + getEvaluationAttitudeAssiduite() +
            ", evaludationValidationGarde=" + getEvaludationValidationGarde() +
            ", evaluationPeriodique=" + getEvaluationPeriodique() +
            ", eCOSM=" + geteCOSM() +
            ", total=" + getTotal() +
            ", isAbsenceSup10pc='" + getIsAbsenceSup10pc() + "'" +
            ", isValide='" + getIsValide() + "'" +
            ", dureeStageComplEnSemaine=" + getDureeStageComplEnSemaine() +
            ", dateDebutStageCompl='" + getDateDebutStageCompl() + "'" +
            ", dateFinStageCompl='" + getDateFinStageCompl() + "'" +
            ", isStageComplValide='" + getIsStageComplValide() + "'" +
            "}";
    }
}
