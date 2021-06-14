package com.fm.mcs.web.rest;

import com.fm.mcs.domain.StageRadiologie;
import com.fm.mcs.repository.StageRadiologieRepository;
import com.fm.mcs.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.fm.mcs.domain.StageRadiologie}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class StageRadiologieResource {

    private final Logger log = LoggerFactory.getLogger(StageRadiologieResource.class);

    private static final String ENTITY_NAME = "stageRadiologie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StageRadiologieRepository stageRadiologieRepository;

    public StageRadiologieResource(StageRadiologieRepository stageRadiologieRepository) {
        this.stageRadiologieRepository = stageRadiologieRepository;
    }

    /**
     * {@code POST  /stage-radiologies} : Create a new stageRadiologie.
     *
     * @param stageRadiologie the stageRadiologie to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stageRadiologie, or with status {@code 400 (Bad Request)} if the stageRadiologie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stage-radiologies")
    public ResponseEntity<StageRadiologie> createStageRadiologie(@Valid @RequestBody StageRadiologie stageRadiologie)
        throws URISyntaxException {
        log.debug("REST request to save StageRadiologie : {}", stageRadiologie);
        if (stageRadiologie.getId() != null) {
            throw new BadRequestAlertException("A new stageRadiologie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StageRadiologie result = stageRadiologieRepository.save(stageRadiologie);
        return ResponseEntity
            .created(new URI("/api/stage-radiologies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stage-radiologies/:id} : Updates an existing stageRadiologie.
     *
     * @param id the id of the stageRadiologie to save.
     * @param stageRadiologie the stageRadiologie to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stageRadiologie,
     * or with status {@code 400 (Bad Request)} if the stageRadiologie is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stageRadiologie couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stage-radiologies/{id}")
    public ResponseEntity<StageRadiologie> updateStageRadiologie(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody StageRadiologie stageRadiologie
    ) throws URISyntaxException {
        log.debug("REST request to update StageRadiologie : {}, {}", id, stageRadiologie);
        if (stageRadiologie.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, stageRadiologie.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!stageRadiologieRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        StageRadiologie result = stageRadiologieRepository.save(stageRadiologie);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stageRadiologie.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /stage-radiologies/:id} : Partial updates given fields of an existing stageRadiologie, field will ignore if it is null
     *
     * @param id the id of the stageRadiologie to save.
     * @param stageRadiologie the stageRadiologie to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stageRadiologie,
     * or with status {@code 400 (Bad Request)} if the stageRadiologie is not valid,
     * or with status {@code 404 (Not Found)} if the stageRadiologie is not found,
     * or with status {@code 500 (Internal Server Error)} if the stageRadiologie couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/stage-radiologies/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<StageRadiologie> partialUpdateStageRadiologie(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody StageRadiologie stageRadiologie
    ) throws URISyntaxException {
        log.debug("REST request to partial update StageRadiologie partially : {}, {}", id, stageRadiologie);
        if (stageRadiologie.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, stageRadiologie.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!stageRadiologieRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<StageRadiologie> result = stageRadiologieRepository
            .findById(stageRadiologie.getId())
            .map(
                existingStageRadiologie -> {
                    if (stageRadiologie.getAnneeEtude() != null) {
                        existingStageRadiologie.setAnneeEtude(stageRadiologie.getAnneeEtude());
                    }
                    if (stageRadiologie.getDateDebut() != null) {
                        existingStageRadiologie.setDateDebut(stageRadiologie.getDateDebut());
                    }
                    if (stageRadiologie.getDateFin() != null) {
                        existingStageRadiologie.setDateFin(stageRadiologie.getDateFin());
                    }
                    if (stageRadiologie.getHopital() != null) {
                        existingStageRadiologie.setHopital(stageRadiologie.getHopital());
                    }
                    if (stageRadiologie.getChefService() != null) {
                        existingStageRadiologie.setChefService(stageRadiologie.getChefService());
                    }
                    if (stageRadiologie.getSemestre() != null) {
                        existingStageRadiologie.setSemestre(stageRadiologie.getSemestre());
                    }
                    if (stageRadiologie.getGroupe() != null) {
                        existingStageRadiologie.setGroupe(stageRadiologie.getGroupe());
                    }
                    if (stageRadiologie.getObjectif1() != null) {
                        existingStageRadiologie.setObjectif1(stageRadiologie.getObjectif1());
                    }
                    if (stageRadiologie.getObjectif1Enseigne() != null) {
                        existingStageRadiologie.setObjectif1Enseigne(stageRadiologie.getObjectif1Enseigne());
                    }
                    if (stageRadiologie.getIsObjectif1Evalue() != null) {
                        existingStageRadiologie.setIsObjectif1Evalue(stageRadiologie.getIsObjectif1Evalue());
                    }
                    if (stageRadiologie.getNoteObjectif1() != null) {
                        existingStageRadiologie.setNoteObjectif1(stageRadiologie.getNoteObjectif1());
                    }
                    if (stageRadiologie.getEvaluationObjectifParObjectif() != null) {
                        existingStageRadiologie.setEvaluationObjectifParObjectif(stageRadiologie.getEvaluationObjectifParObjectif());
                    }
                    if (stageRadiologie.getEvaluationRedactionObservation() != null) {
                        existingStageRadiologie.setEvaluationRedactionObservation(stageRadiologie.getEvaluationRedactionObservation());
                    }
                    if (stageRadiologie.getEvaluationElaborationResume() != null) {
                        existingStageRadiologie.setEvaluationElaborationResume(stageRadiologie.getEvaluationElaborationResume());
                    }
                    if (stageRadiologie.getEvaluationAttitudeAssiduite() != null) {
                        existingStageRadiologie.setEvaluationAttitudeAssiduite(stageRadiologie.getEvaluationAttitudeAssiduite());
                    }
                    if (stageRadiologie.getEvaludationValidationGarde() != null) {
                        existingStageRadiologie.setEvaludationValidationGarde(stageRadiologie.getEvaludationValidationGarde());
                    }
                    if (stageRadiologie.getEvaluationPeriodique() != null) {
                        existingStageRadiologie.setEvaluationPeriodique(stageRadiologie.getEvaluationPeriodique());
                    }
                    if (stageRadiologie.geteCOSM() != null) {
                        existingStageRadiologie.seteCOSM(stageRadiologie.geteCOSM());
                    }
                    if (stageRadiologie.getTotal() != null) {
                        existingStageRadiologie.setTotal(stageRadiologie.getTotal());
                    }
                    if (stageRadiologie.getIsAbsenceSup10pc() != null) {
                        existingStageRadiologie.setIsAbsenceSup10pc(stageRadiologie.getIsAbsenceSup10pc());
                    }
                    if (stageRadiologie.getIsValide() != null) {
                        existingStageRadiologie.setIsValide(stageRadiologie.getIsValide());
                    }
                    if (stageRadiologie.getDureeStageComplEnSemaine() != null) {
                        existingStageRadiologie.setDureeStageComplEnSemaine(stageRadiologie.getDureeStageComplEnSemaine());
                    }
                    if (stageRadiologie.getDateDebutStageCompl() != null) {
                        existingStageRadiologie.setDateDebutStageCompl(stageRadiologie.getDateDebutStageCompl());
                    }
                    if (stageRadiologie.getDateFinStageCompl() != null) {
                        existingStageRadiologie.setDateFinStageCompl(stageRadiologie.getDateFinStageCompl());
                    }
                    if (stageRadiologie.getIsStageComplValide() != null) {
                        existingStageRadiologie.setIsStageComplValide(stageRadiologie.getIsStageComplValide());
                    }

                    return existingStageRadiologie;
                }
            )
            .map(stageRadiologieRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stageRadiologie.getId().toString())
        );
    }

    /**
     * {@code GET  /stage-radiologies} : get all the stageRadiologies.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stageRadiologies in body.
     */
    @GetMapping("/stage-radiologies")
    public ResponseEntity<List<StageRadiologie>> getAllStageRadiologies(Pageable pageable) {
        log.debug("REST request to get a page of StageRadiologies");
        Page<StageRadiologie> page = stageRadiologieRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /stage-radiologies/:id} : get the "id" stageRadiologie.
     *
     * @param id the id of the stageRadiologie to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stageRadiologie, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stage-radiologies/{id}")
    public ResponseEntity<StageRadiologie> getStageRadiologie(@PathVariable Long id) {
        log.debug("REST request to get StageRadiologie : {}", id);
        Optional<StageRadiologie> stageRadiologie = stageRadiologieRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(stageRadiologie);
    }

    /**
     * {@code DELETE  /stage-radiologies/:id} : delete the "id" stageRadiologie.
     *
     * @param id the id of the stageRadiologie to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stage-radiologies/{id}")
    public ResponseEntity<Void> deleteStageRadiologie(@PathVariable Long id) {
        log.debug("REST request to delete StageRadiologie : {}", id);
        stageRadiologieRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
