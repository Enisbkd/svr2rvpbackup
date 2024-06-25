package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import static com.sbm.mc.sevenroomstoreviewpro.domain.RvpProfileAsserts.*;
import static com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil.createUpdateProxyForBean;
import static com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.mc.sevenroomstoreviewpro.IntegrationTest;
import com.sbm.mc.sevenroomstoreviewpro.domain.RvpProfile;
import com.sbm.mc.sevenroomstoreviewpro.repository.RvpProfileRepository;
import jakarta.persistence.EntityManager;
import java.time.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RvpProfileResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RvpProfileResourceIT {

    private static final String DEFAULT_PMS_ID = "AAAAAAAAAA";
    private static final String UPDATED_PMS_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CHECKIN = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CHECKIN = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CHECKOUT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CHECKOUT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/rvp-profiles";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RvpProfileRepository rvpProfileRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRvpProfileMockMvc;

    private RvpProfile rvpProfile;

    private RvpProfile insertedRvpProfile;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RvpProfile createEntity(EntityManager em) {
        RvpProfile rvpProfile = new RvpProfile()
            .pmsId(DEFAULT_PMS_ID)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .language(DEFAULT_LANGUAGE)
            .checkin(DEFAULT_CHECKIN)
            .checkout(DEFAULT_CHECKOUT)
            .email(DEFAULT_EMAIL);
        return rvpProfile;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RvpProfile createUpdatedEntity(EntityManager em) {
        RvpProfile rvpProfile = new RvpProfile()
            .pmsId(UPDATED_PMS_ID)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .language(UPDATED_LANGUAGE)
            .checkin(UPDATED_CHECKIN)
            .checkout(UPDATED_CHECKOUT)
            .email(UPDATED_EMAIL);
        return rvpProfile;
    }

    @BeforeEach
    public void initTest() {
        rvpProfile = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedRvpProfile != null) {
            rvpProfileRepository.delete(insertedRvpProfile);
            insertedRvpProfile = null;
        }
    }

    @Test
    @Transactional
    void createRvpProfile() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the RvpProfile
        var returnedRvpProfile = om.readValue(
            restRvpProfileMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rvpProfile)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            RvpProfile.class
        );

        // Validate the RvpProfile in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertRvpProfileUpdatableFieldsEquals(returnedRvpProfile, getPersistedRvpProfile(returnedRvpProfile));

        insertedRvpProfile = returnedRvpProfile;
    }

    @Test
    @Transactional
    void createRvpProfileWithExistingId() throws Exception {
        // Create the RvpProfile with an existing ID
        rvpProfile.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRvpProfileMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rvpProfile)))
            .andExpect(status().isBadRequest());

        // Validate the RvpProfile in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRvpProfiles() throws Exception {
        // Initialize the database
        insertedRvpProfile = rvpProfileRepository.saveAndFlush(rvpProfile);

        // Get all the rvpProfileList
        restRvpProfileMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rvpProfile.getId().intValue())))
            .andExpect(jsonPath("$.[*].pmsId").value(hasItem(DEFAULT_PMS_ID)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE)))
            .andExpect(jsonPath("$.[*].checkin").value(hasItem(sameInstant(DEFAULT_CHECKIN))))
            .andExpect(jsonPath("$.[*].checkout").value(hasItem(sameInstant(DEFAULT_CHECKOUT))))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }

    @Test
    @Transactional
    void getRvpProfile() throws Exception {
        // Initialize the database
        insertedRvpProfile = rvpProfileRepository.saveAndFlush(rvpProfile);

        // Get the rvpProfile
        restRvpProfileMockMvc
            .perform(get(ENTITY_API_URL_ID, rvpProfile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rvpProfile.getId().intValue()))
            .andExpect(jsonPath("$.pmsId").value(DEFAULT_PMS_ID))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE))
            .andExpect(jsonPath("$.checkin").value(sameInstant(DEFAULT_CHECKIN)))
            .andExpect(jsonPath("$.checkout").value(sameInstant(DEFAULT_CHECKOUT)))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }

    @Test
    @Transactional
    void getNonExistingRvpProfile() throws Exception {
        // Get the rvpProfile
        restRvpProfileMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRvpProfile() throws Exception {
        // Initialize the database
        insertedRvpProfile = rvpProfileRepository.saveAndFlush(rvpProfile);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rvpProfile
        RvpProfile updatedRvpProfile = rvpProfileRepository.findById(rvpProfile.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedRvpProfile are not directly saved in db
        em.detach(updatedRvpProfile);
        updatedRvpProfile
            .pmsId(UPDATED_PMS_ID)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .language(UPDATED_LANGUAGE)
            .checkin(UPDATED_CHECKIN.toLocalDateTime())
            .checkout(LocalDate.from(UPDATED_CHECKOUT))
            .email(UPDATED_EMAIL);

        restRvpProfileMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRvpProfile.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedRvpProfile))
            )
            .andExpect(status().isOk());

        // Validate the RvpProfile in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRvpProfileToMatchAllProperties(updatedRvpProfile);
    }

    @Test
    @Transactional
    void putNonExistingRvpProfile() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpProfile.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRvpProfileMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rvpProfile.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rvpProfile))
            )
            .andExpect(status().isBadRequest());

        // Validate the RvpProfile in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRvpProfile() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpProfile.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRvpProfileMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(rvpProfile))
            )
            .andExpect(status().isBadRequest());

        // Validate the RvpProfile in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRvpProfile() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpProfile.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRvpProfileMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rvpProfile)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RvpProfile in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRvpProfileWithPatch() throws Exception {
        // Initialize the database
        insertedRvpProfile = rvpProfileRepository.saveAndFlush(rvpProfile);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rvpProfile using partial update
        RvpProfile partialUpdatedRvpProfile = new RvpProfile();
        partialUpdatedRvpProfile.setId(rvpProfile.getId());

        partialUpdatedRvpProfile
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .checkin(UPDATED_CHECKIN.toLocalDateTime())
            .email(UPDATED_EMAIL);

        restRvpProfileMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRvpProfile.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRvpProfile))
            )
            .andExpect(status().isOk());

        // Validate the RvpProfile in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRvpProfileUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedRvpProfile, rvpProfile),
            getPersistedRvpProfile(rvpProfile)
        );
    }

    @Test
    @Transactional
    void fullUpdateRvpProfileWithPatch() throws Exception {
        // Initialize the database
        insertedRvpProfile = rvpProfileRepository.saveAndFlush(rvpProfile);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rvpProfile using partial update
        RvpProfile partialUpdatedRvpProfile = new RvpProfile();
        partialUpdatedRvpProfile.setId(rvpProfile.getId());

        partialUpdatedRvpProfile
            .pmsId(UPDATED_PMS_ID)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .language(UPDATED_LANGUAGE)
            .checkin(UPDATED_CHECKIN)
            .checkout(UPDATED_CHECKOUT)
            .email(UPDATED_EMAIL);

        restRvpProfileMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRvpProfile.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRvpProfile))
            )
            .andExpect(status().isOk());

        // Validate the RvpProfile in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRvpProfileUpdatableFieldsEquals(partialUpdatedRvpProfile, getPersistedRvpProfile(partialUpdatedRvpProfile));
    }

    @Test
    @Transactional
    void patchNonExistingRvpProfile() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpProfile.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRvpProfileMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, rvpProfile.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(rvpProfile))
            )
            .andExpect(status().isBadRequest());

        // Validate the RvpProfile in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRvpProfile() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpProfile.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRvpProfileMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(rvpProfile))
            )
            .andExpect(status().isBadRequest());

        // Validate the RvpProfile in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRvpProfile() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpProfile.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRvpProfileMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(rvpProfile)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RvpProfile in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRvpProfile() throws Exception {
        // Initialize the database
        insertedRvpProfile = rvpProfileRepository.saveAndFlush(rvpProfile);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the rvpProfile
        restRvpProfileMockMvc
            .perform(delete(ENTITY_API_URL_ID, rvpProfile.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return rvpProfileRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected RvpProfile getPersistedRvpProfile(RvpProfile rvpProfile) {
        return rvpProfileRepository.findById(rvpProfile.getId()).orElseThrow();
    }

    protected void assertPersistedRvpProfileToMatchAllProperties(RvpProfile expectedRvpProfile) {
        assertRvpProfileAllPropertiesEquals(expectedRvpProfile, getPersistedRvpProfile(expectedRvpProfile));
    }

    protected void assertPersistedRvpProfileToMatchUpdatableProperties(RvpProfile expectedRvpProfile) {
        assertRvpProfileAllUpdatablePropertiesEquals(expectedRvpProfile, getPersistedRvpProfile(expectedRvpProfile));
    }
}
