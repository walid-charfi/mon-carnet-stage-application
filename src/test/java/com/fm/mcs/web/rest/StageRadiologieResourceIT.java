package com.fm.mcs.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fm.mcs.IntegrationTest;
import com.fm.mcs.domain.StageRadiologie;
import com.fm.mcs.domain.enumeration.AnneeEtude;
import com.fm.mcs.domain.enumeration.Hopital;
import com.fm.mcs.repository.StageRadiologieRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link StageRadiologieResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StageRadiologieResourceIT {

    private static final AnneeEtude DEFAULT_ANNEE_ETUDE = AnneeEtude.DCEM1;
    private static final AnneeEtude UPDATED_ANNEE_ETUDE = AnneeEtude.DCEM2;

    private static final LocalDate DEFAULT_DATE_DEBUT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEBUT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final Hopital DEFAULT_HOPITAL = Hopital.CharlesNicolle;
    private static final Hopital UPDATED_HOPITAL = Hopital.MongiSlim;

    private static final String DEFAULT_CHEF_SERVICE = "AAAAAAAAAA";
    private static final String UPDATED_CHEF_SERVICE = "BBBBBBBBBB";

    private static final String DEFAULT_SEMESTRE = "AAAAAAAAAA";
    private static final String UPDATED_SEMESTRE = "BBBBBBBBBB";

    private static final String DEFAULT_GROUPE = "AAAAAAAAAA";
    private static final String UPDATED_GROUPE = "BBBBBBBBBB";

    private static final String DEFAULT_OBJECTIF_1 = "AAAAAAAAAA";
    private static final String UPDATED_OBJECTIF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_OBJECTIF_1_ENSEIGNE = "AAAAAAAAAA";
    private static final String UPDATED_OBJECTIF_1_ENSEIGNE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NOTE_OBJECTIF_1 = 1;
    private static final Integer UPDATED_NOTE_OBJECTIF_1 = 2;

    private static final String ENTITY_API_URL = "/api/stage-radiologies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StageRadiologieRepository stageRadiologieRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStageRadiologieMockMvc;

    private StageRadiologie stageRadiologie;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StageRadiologie createEntity(EntityManager em) {
        StageRadiologie stageRadiologie = new StageRadiologie()
            .anneeEtude(DEFAULT_ANNEE_ETUDE)
            .dateDebut(DEFAULT_DATE_DEBUT)
            .dateFin(DEFAULT_DATE_FIN)
            .hopital(DEFAULT_HOPITAL)
            .chefService(DEFAULT_CHEF_SERVICE)
            .semestre(DEFAULT_SEMESTRE)
            .groupe(DEFAULT_GROUPE)
            .objectif1(DEFAULT_OBJECTIF_1)
            .objectif1Enseigne(DEFAULT_OBJECTIF_1_ENSEIGNE)
            .noteObjectif1(DEFAULT_NOTE_OBJECTIF_1);
        return stageRadiologie;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StageRadiologie createUpdatedEntity(EntityManager em) {
        StageRadiologie stageRadiologie = new StageRadiologie()
            .anneeEtude(UPDATED_ANNEE_ETUDE)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .hopital(UPDATED_HOPITAL)
            .chefService(UPDATED_CHEF_SERVICE)
            .semestre(UPDATED_SEMESTRE)
            .groupe(UPDATED_GROUPE)
            .objectif1(UPDATED_OBJECTIF_1)
            .objectif1Enseigne(UPDATED_OBJECTIF_1_ENSEIGNE)
            .noteObjectif1(UPDATED_NOTE_OBJECTIF_1);
        return stageRadiologie;
    }

    @BeforeEach
    public void initTest() {
        stageRadiologie = createEntity(em);
    }

    @Test
    @Transactional
    void createStageRadiologie() throws Exception {
        int databaseSizeBeforeCreate = stageRadiologieRepository.findAll().size();
        // Create the StageRadiologie
        restStageRadiologieMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(stageRadiologie))
            )
            .andExpect(status().isCreated());

        // Validate the StageRadiologie in the database
        List<StageRadiologie> stageRadiologieList = stageRadiologieRepository.findAll();
        assertThat(stageRadiologieList).hasSize(databaseSizeBeforeCreate + 1);
        StageRadiologie testStageRadiologie = stageRadiologieList.get(stageRadiologieList.size() - 1);
        assertThat(testStageRadiologie.getAnneeEtude()).isEqualTo(DEFAULT_ANNEE_ETUDE);
        assertThat(testStageRadiologie.getDateDebut()).isEqualTo(DEFAULT_DATE_DEBUT);
        assertThat(testStageRadiologie.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
        assertThat(testStageRadiologie.getHopital()).isEqualTo(DEFAULT_HOPITAL);
        assertThat(testStageRadiologie.getChefService()).isEqualTo(DEFAULT_CHEF_SERVICE);
        assertThat(testStageRadiologie.getSemestre()).isEqualTo(DEFAULT_SEMESTRE);
        assertThat(testStageRadiologie.getGroupe()).isEqualTo(DEFAULT_GROUPE);
        assertThat(testStageRadiologie.getObjectif1()).isEqualTo(DEFAULT_OBJECTIF_1);
        assertThat(testStageRadiologie.getObjectif1Enseigne()).isEqualTo(DEFAULT_OBJECTIF_1_ENSEIGNE);
        assertThat(testStageRadiologie.getNoteObjectif1()).isEqualTo(DEFAULT_NOTE_OBJECTIF_1);
    }

    @Test
    @Transactional
    void createStageRadiologieWithExistingId() throws Exception {
        // Create the StageRadiologie with an existing ID
        stageRadiologie.setId(1L);

        int databaseSizeBeforeCreate = stageRadiologieRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStageRadiologieMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(stageRadiologie))
            )
            .andExpect(status().isBadRequest());

        // Validate the StageRadiologie in the database
        List<StageRadiologie> stageRadiologieList = stageRadiologieRepository.findAll();
        assertThat(stageRadiologieList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkAnneeEtudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = stageRadiologieRepository.findAll().size();
        // set the field null
        stageRadiologie.setAnneeEtude(null);

        // Create the StageRadiologie, which fails.

        restStageRadiologieMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(stageRadiologie))
            )
            .andExpect(status().isBadRequest());

        List<StageRadiologie> stageRadiologieList = stageRadiologieRepository.findAll();
        assertThat(stageRadiologieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkHopitalIsRequired() throws Exception {
        int databaseSizeBeforeTest = stageRadiologieRepository.findAll().size();
        // set the field null
        stageRadiologie.setHopital(null);

        // Create the StageRadiologie, which fails.

        restStageRadiologieMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(stageRadiologie))
            )
            .andExpect(status().isBadRequest());

        List<StageRadiologie> stageRadiologieList = stageRadiologieRepository.findAll();
        assertThat(stageRadiologieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllStageRadiologies() throws Exception {
        // Initialize the database
        stageRadiologieRepository.saveAndFlush(stageRadiologie);

        // Get all the stageRadiologieList
        restStageRadiologieMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stageRadiologie.getId().intValue())))
            .andExpect(jsonPath("$.[*].anneeEtude").value(hasItem(DEFAULT_ANNEE_ETUDE.toString())))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(DEFAULT_DATE_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(DEFAULT_DATE_FIN.toString())))
            .andExpect(jsonPath("$.[*].hopital").value(hasItem(DEFAULT_HOPITAL.toString())))
            .andExpect(jsonPath("$.[*].chefService").value(hasItem(DEFAULT_CHEF_SERVICE)))
            .andExpect(jsonPath("$.[*].semestre").value(hasItem(DEFAULT_SEMESTRE)))
            .andExpect(jsonPath("$.[*].groupe").value(hasItem(DEFAULT_GROUPE)))
            .andExpect(jsonPath("$.[*].objectif1").value(hasItem(DEFAULT_OBJECTIF_1)))
            .andExpect(jsonPath("$.[*].objectif1Enseigne").value(hasItem(DEFAULT_OBJECTIF_1_ENSEIGNE)))
            .andExpect(jsonPath("$.[*].noteObjectif1").value(hasItem(DEFAULT_NOTE_OBJECTIF_1)));
    }

    @Test
    @Transactional
    void getStageRadiologie() throws Exception {
        // Initialize the database
        stageRadiologieRepository.saveAndFlush(stageRadiologie);

        // Get the stageRadiologie
        restStageRadiologieMockMvc
            .perform(get(ENTITY_API_URL_ID, stageRadiologie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(stageRadiologie.getId().intValue()))
            .andExpect(jsonPath("$.anneeEtude").value(DEFAULT_ANNEE_ETUDE.toString()))
            .andExpect(jsonPath("$.dateDebut").value(DEFAULT_DATE_DEBUT.toString()))
            .andExpect(jsonPath("$.dateFin").value(DEFAULT_DATE_FIN.toString()))
            .andExpect(jsonPath("$.hopital").value(DEFAULT_HOPITAL.toString()))
            .andExpect(jsonPath("$.chefService").value(DEFAULT_CHEF_SERVICE))
            .andExpect(jsonPath("$.semestre").value(DEFAULT_SEMESTRE))
            .andExpect(jsonPath("$.groupe").value(DEFAULT_GROUPE))
            .andExpect(jsonPath("$.objectif1").value(DEFAULT_OBJECTIF_1))
            .andExpect(jsonPath("$.objectif1Enseigne").value(DEFAULT_OBJECTIF_1_ENSEIGNE))
            .andExpect(jsonPath("$.noteObjectif1").value(DEFAULT_NOTE_OBJECTIF_1));
    }

    @Test
    @Transactional
    void getNonExistingStageRadiologie() throws Exception {
        // Get the stageRadiologie
        restStageRadiologieMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewStageRadiologie() throws Exception {
        // Initialize the database
        stageRadiologieRepository.saveAndFlush(stageRadiologie);

        int databaseSizeBeforeUpdate = stageRadiologieRepository.findAll().size();

        // Update the stageRadiologie
        StageRadiologie updatedStageRadiologie = stageRadiologieRepository.findById(stageRadiologie.getId()).get();
        // Disconnect from session so that the updates on updatedStageRadiologie are not directly saved in db
        em.detach(updatedStageRadiologie);
        updatedStageRadiologie
            .anneeEtude(UPDATED_ANNEE_ETUDE)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .hopital(UPDATED_HOPITAL)
            .chefService(UPDATED_CHEF_SERVICE)
            .semestre(UPDATED_SEMESTRE)
            .groupe(UPDATED_GROUPE)
            .objectif1(UPDATED_OBJECTIF_1)
            .objectif1Enseigne(UPDATED_OBJECTIF_1_ENSEIGNE)
            .noteObjectif1(UPDATED_NOTE_OBJECTIF_1);

        restStageRadiologieMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedStageRadiologie.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedStageRadiologie))
            )
            .andExpect(status().isOk());

        // Validate the StageRadiologie in the database
        List<StageRadiologie> stageRadiologieList = stageRadiologieRepository.findAll();
        assertThat(stageRadiologieList).hasSize(databaseSizeBeforeUpdate);
        StageRadiologie testStageRadiologie = stageRadiologieList.get(stageRadiologieList.size() - 1);
        assertThat(testStageRadiologie.getAnneeEtude()).isEqualTo(UPDATED_ANNEE_ETUDE);
        assertThat(testStageRadiologie.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testStageRadiologie.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
        assertThat(testStageRadiologie.getHopital()).isEqualTo(UPDATED_HOPITAL);
        assertThat(testStageRadiologie.getChefService()).isEqualTo(UPDATED_CHEF_SERVICE);
        assertThat(testStageRadiologie.getSemestre()).isEqualTo(UPDATED_SEMESTRE);
        assertThat(testStageRadiologie.getGroupe()).isEqualTo(UPDATED_GROUPE);
        assertThat(testStageRadiologie.getObjectif1()).isEqualTo(UPDATED_OBJECTIF_1);
        assertThat(testStageRadiologie.getObjectif1Enseigne()).isEqualTo(UPDATED_OBJECTIF_1_ENSEIGNE);
        assertThat(testStageRadiologie.getNoteObjectif1()).isEqualTo(UPDATED_NOTE_OBJECTIF_1);
    }

    @Test
    @Transactional
    void putNonExistingStageRadiologie() throws Exception {
        int databaseSizeBeforeUpdate = stageRadiologieRepository.findAll().size();
        stageRadiologie.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStageRadiologieMockMvc
            .perform(
                put(ENTITY_API_URL_ID, stageRadiologie.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stageRadiologie))
            )
            .andExpect(status().isBadRequest());

        // Validate the StageRadiologie in the database
        List<StageRadiologie> stageRadiologieList = stageRadiologieRepository.findAll();
        assertThat(stageRadiologieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchStageRadiologie() throws Exception {
        int databaseSizeBeforeUpdate = stageRadiologieRepository.findAll().size();
        stageRadiologie.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStageRadiologieMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stageRadiologie))
            )
            .andExpect(status().isBadRequest());

        // Validate the StageRadiologie in the database
        List<StageRadiologie> stageRadiologieList = stageRadiologieRepository.findAll();
        assertThat(stageRadiologieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamStageRadiologie() throws Exception {
        int databaseSizeBeforeUpdate = stageRadiologieRepository.findAll().size();
        stageRadiologie.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStageRadiologieMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(stageRadiologie))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the StageRadiologie in the database
        List<StageRadiologie> stageRadiologieList = stageRadiologieRepository.findAll();
        assertThat(stageRadiologieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateStageRadiologieWithPatch() throws Exception {
        // Initialize the database
        stageRadiologieRepository.saveAndFlush(stageRadiologie);

        int databaseSizeBeforeUpdate = stageRadiologieRepository.findAll().size();

        // Update the stageRadiologie using partial update
        StageRadiologie partialUpdatedStageRadiologie = new StageRadiologie();
        partialUpdatedStageRadiologie.setId(stageRadiologie.getId());

        partialUpdatedStageRadiologie
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .hopital(UPDATED_HOPITAL)
            .chefService(UPDATED_CHEF_SERVICE)
            .semestre(UPDATED_SEMESTRE)
            .groupe(UPDATED_GROUPE);

        restStageRadiologieMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStageRadiologie.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStageRadiologie))
            )
            .andExpect(status().isOk());

        // Validate the StageRadiologie in the database
        List<StageRadiologie> stageRadiologieList = stageRadiologieRepository.findAll();
        assertThat(stageRadiologieList).hasSize(databaseSizeBeforeUpdate);
        StageRadiologie testStageRadiologie = stageRadiologieList.get(stageRadiologieList.size() - 1);
        assertThat(testStageRadiologie.getAnneeEtude()).isEqualTo(DEFAULT_ANNEE_ETUDE);
        assertThat(testStageRadiologie.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testStageRadiologie.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
        assertThat(testStageRadiologie.getHopital()).isEqualTo(UPDATED_HOPITAL);
        assertThat(testStageRadiologie.getChefService()).isEqualTo(UPDATED_CHEF_SERVICE);
        assertThat(testStageRadiologie.getSemestre()).isEqualTo(UPDATED_SEMESTRE);
        assertThat(testStageRadiologie.getGroupe()).isEqualTo(UPDATED_GROUPE);
        assertThat(testStageRadiologie.getObjectif1()).isEqualTo(DEFAULT_OBJECTIF_1);
        assertThat(testStageRadiologie.getObjectif1Enseigne()).isEqualTo(DEFAULT_OBJECTIF_1_ENSEIGNE);
        assertThat(testStageRadiologie.getNoteObjectif1()).isEqualTo(DEFAULT_NOTE_OBJECTIF_1);
    }

    @Test
    @Transactional
    void fullUpdateStageRadiologieWithPatch() throws Exception {
        // Initialize the database
        stageRadiologieRepository.saveAndFlush(stageRadiologie);

        int databaseSizeBeforeUpdate = stageRadiologieRepository.findAll().size();

        // Update the stageRadiologie using partial update
        StageRadiologie partialUpdatedStageRadiologie = new StageRadiologie();
        partialUpdatedStageRadiologie.setId(stageRadiologie.getId());

        partialUpdatedStageRadiologie
            .anneeEtude(UPDATED_ANNEE_ETUDE)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .hopital(UPDATED_HOPITAL)
            .chefService(UPDATED_CHEF_SERVICE)
            .semestre(UPDATED_SEMESTRE)
            .groupe(UPDATED_GROUPE)
            .objectif1(UPDATED_OBJECTIF_1)
            .objectif1Enseigne(UPDATED_OBJECTIF_1_ENSEIGNE)
            .noteObjectif1(UPDATED_NOTE_OBJECTIF_1);

        restStageRadiologieMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStageRadiologie.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStageRadiologie))
            )
            .andExpect(status().isOk());

        // Validate the StageRadiologie in the database
        List<StageRadiologie> stageRadiologieList = stageRadiologieRepository.findAll();
        assertThat(stageRadiologieList).hasSize(databaseSizeBeforeUpdate);
        StageRadiologie testStageRadiologie = stageRadiologieList.get(stageRadiologieList.size() - 1);
        assertThat(testStageRadiologie.getAnneeEtude()).isEqualTo(UPDATED_ANNEE_ETUDE);
        assertThat(testStageRadiologie.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testStageRadiologie.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
        assertThat(testStageRadiologie.getHopital()).isEqualTo(UPDATED_HOPITAL);
        assertThat(testStageRadiologie.getChefService()).isEqualTo(UPDATED_CHEF_SERVICE);
        assertThat(testStageRadiologie.getSemestre()).isEqualTo(UPDATED_SEMESTRE);
        assertThat(testStageRadiologie.getGroupe()).isEqualTo(UPDATED_GROUPE);
        assertThat(testStageRadiologie.getObjectif1()).isEqualTo(UPDATED_OBJECTIF_1);
        assertThat(testStageRadiologie.getObjectif1Enseigne()).isEqualTo(UPDATED_OBJECTIF_1_ENSEIGNE);
        assertThat(testStageRadiologie.getNoteObjectif1()).isEqualTo(UPDATED_NOTE_OBJECTIF_1);
    }

    @Test
    @Transactional
    void patchNonExistingStageRadiologie() throws Exception {
        int databaseSizeBeforeUpdate = stageRadiologieRepository.findAll().size();
        stageRadiologie.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStageRadiologieMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, stageRadiologie.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stageRadiologie))
            )
            .andExpect(status().isBadRequest());

        // Validate the StageRadiologie in the database
        List<StageRadiologie> stageRadiologieList = stageRadiologieRepository.findAll();
        assertThat(stageRadiologieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchStageRadiologie() throws Exception {
        int databaseSizeBeforeUpdate = stageRadiologieRepository.findAll().size();
        stageRadiologie.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStageRadiologieMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stageRadiologie))
            )
            .andExpect(status().isBadRequest());

        // Validate the StageRadiologie in the database
        List<StageRadiologie> stageRadiologieList = stageRadiologieRepository.findAll();
        assertThat(stageRadiologieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamStageRadiologie() throws Exception {
        int databaseSizeBeforeUpdate = stageRadiologieRepository.findAll().size();
        stageRadiologie.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStageRadiologieMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stageRadiologie))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the StageRadiologie in the database
        List<StageRadiologie> stageRadiologieList = stageRadiologieRepository.findAll();
        assertThat(stageRadiologieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteStageRadiologie() throws Exception {
        // Initialize the database
        stageRadiologieRepository.saveAndFlush(stageRadiologie);

        int databaseSizeBeforeDelete = stageRadiologieRepository.findAll().size();

        // Delete the stageRadiologie
        restStageRadiologieMockMvc
            .perform(delete(ENTITY_API_URL_ID, stageRadiologie.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StageRadiologie> stageRadiologieList = stageRadiologieRepository.findAll();
        assertThat(stageRadiologieList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
