package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import static com.sbm.mc.sevenroomstoreviewpro.domain.ResCustomFieldAsserts.*;
import static com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.mc.sevenroomstoreviewpro.IntegrationTest;
import com.sbm.mc.sevenroomstoreviewpro.domain.ResCustomField;
import com.sbm.mc.sevenroomstoreviewpro.repository.ResCustomFieldRepository;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link ResCustomFieldResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResCustomFieldResourceIT {

    private static final String DEFAULT_SYSTEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEM_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_DISPLAY_ORDER = 1;
    private static final Integer UPDATED_DISPLAY_ORDER = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/res-custom-fields";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ResCustomFieldRepository resCustomFieldRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResCustomFieldMockMvc;

    private ResCustomField resCustomField;

    private ResCustomField insertedResCustomField;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResCustomField createEntity(EntityManager em) {
        ResCustomField resCustomField = new ResCustomField()
            .systemName(DEFAULT_SYSTEM_NAME)
            .displayOrder(DEFAULT_DISPLAY_ORDER)
            .name(DEFAULT_NAME)
            .value(DEFAULT_VALUE);
        return resCustomField;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResCustomField createUpdatedEntity(EntityManager em) {
        ResCustomField resCustomField = new ResCustomField()
            .systemName(UPDATED_SYSTEM_NAME)
            .displayOrder(UPDATED_DISPLAY_ORDER)
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE);
        return resCustomField;
    }

    @BeforeEach
    public void initTest() {
        resCustomField = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedResCustomField != null) {
            resCustomFieldRepository.delete(insertedResCustomField);
            insertedResCustomField = null;
        }
    }

    @Test
    @Transactional
    void createResCustomField() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ResCustomField
        var returnedResCustomField = om.readValue(
            restResCustomFieldMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resCustomField)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ResCustomField.class
        );

        // Validate the ResCustomField in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertResCustomFieldUpdatableFieldsEquals(returnedResCustomField, getPersistedResCustomField(returnedResCustomField));

        insertedResCustomField = returnedResCustomField;
    }

    @Test
    @Transactional
    void createResCustomFieldWithExistingId() throws Exception {
        // Create the ResCustomField with an existing ID
        resCustomField.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restResCustomFieldMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resCustomField)))
            .andExpect(status().isBadRequest());

        // Validate the ResCustomField in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllResCustomFields() throws Exception {
        // Initialize the database
        insertedResCustomField = resCustomFieldRepository.saveAndFlush(resCustomField);

        // Get all the resCustomFieldList
        restResCustomFieldMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resCustomField.getId().intValue())))
            .andExpect(jsonPath("$.[*].systemName").value(hasItem(DEFAULT_SYSTEM_NAME)))
            .andExpect(jsonPath("$.[*].displayOrder").value(hasItem(DEFAULT_DISPLAY_ORDER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));
    }

    @Test
    @Transactional
    void getResCustomField() throws Exception {
        // Initialize the database
        insertedResCustomField = resCustomFieldRepository.saveAndFlush(resCustomField);

        // Get the resCustomField
        restResCustomFieldMockMvc
            .perform(get(ENTITY_API_URL_ID, resCustomField.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resCustomField.getId().intValue()))
            .andExpect(jsonPath("$.systemName").value(DEFAULT_SYSTEM_NAME))
            .andExpect(jsonPath("$.displayOrder").value(DEFAULT_DISPLAY_ORDER))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE));
    }

    @Test
    @Transactional
    void getNonExistingResCustomField() throws Exception {
        // Get the resCustomField
        restResCustomFieldMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingResCustomField() throws Exception {
        // Initialize the database
        insertedResCustomField = resCustomFieldRepository.saveAndFlush(resCustomField);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resCustomField
        ResCustomField updatedResCustomField = resCustomFieldRepository.findById(resCustomField.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedResCustomField are not directly saved in db
        em.detach(updatedResCustomField);
        updatedResCustomField.systemName(UPDATED_SYSTEM_NAME).displayOrder(UPDATED_DISPLAY_ORDER).name(UPDATED_NAME).value(UPDATED_VALUE);

        restResCustomFieldMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedResCustomField.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedResCustomField))
            )
            .andExpect(status().isOk());

        // Validate the ResCustomField in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedResCustomFieldToMatchAllProperties(updatedResCustomField);
    }

    @Test
    @Transactional
    void putNonExistingResCustomField() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resCustomField.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResCustomFieldMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resCustomField.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(resCustomField))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResCustomField in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchResCustomField() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resCustomField.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResCustomFieldMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(resCustomField))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResCustomField in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamResCustomField() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resCustomField.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResCustomFieldMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resCustomField)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResCustomField in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateResCustomFieldWithPatch() throws Exception {
        // Initialize the database
        insertedResCustomField = resCustomFieldRepository.saveAndFlush(resCustomField);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resCustomField using partial update
        ResCustomField partialUpdatedResCustomField = new ResCustomField();
        partialUpdatedResCustomField.setId(resCustomField.getId());

        restResCustomFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResCustomField.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedResCustomField))
            )
            .andExpect(status().isOk());

        // Validate the ResCustomField in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertResCustomFieldUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedResCustomField, resCustomField),
            getPersistedResCustomField(resCustomField)
        );
    }

    @Test
    @Transactional
    void fullUpdateResCustomFieldWithPatch() throws Exception {
        // Initialize the database
        insertedResCustomField = resCustomFieldRepository.saveAndFlush(resCustomField);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resCustomField using partial update
        ResCustomField partialUpdatedResCustomField = new ResCustomField();
        partialUpdatedResCustomField.setId(resCustomField.getId());

        partialUpdatedResCustomField
            .systemName(UPDATED_SYSTEM_NAME)
            .displayOrder(UPDATED_DISPLAY_ORDER)
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE);

        restResCustomFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResCustomField.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedResCustomField))
            )
            .andExpect(status().isOk());

        // Validate the ResCustomField in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertResCustomFieldUpdatableFieldsEquals(partialUpdatedResCustomField, getPersistedResCustomField(partialUpdatedResCustomField));
    }

    @Test
    @Transactional
    void patchNonExistingResCustomField() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resCustomField.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResCustomFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, resCustomField.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(resCustomField))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResCustomField in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchResCustomField() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resCustomField.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResCustomFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(resCustomField))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResCustomField in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamResCustomField() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resCustomField.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResCustomFieldMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(resCustomField)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResCustomField in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteResCustomField() throws Exception {
        // Initialize the database
        insertedResCustomField = resCustomFieldRepository.saveAndFlush(resCustomField);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the resCustomField
        restResCustomFieldMockMvc
            .perform(delete(ENTITY_API_URL_ID, resCustomField.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return resCustomFieldRepository.count();
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

    protected ResCustomField getPersistedResCustomField(ResCustomField resCustomField) {
        return resCustomFieldRepository.findById(resCustomField.getId()).orElseThrow();
    }

    protected void assertPersistedResCustomFieldToMatchAllProperties(ResCustomField expectedResCustomField) {
        assertResCustomFieldAllPropertiesEquals(expectedResCustomField, getPersistedResCustomField(expectedResCustomField));
    }

    protected void assertPersistedResCustomFieldToMatchUpdatableProperties(ResCustomField expectedResCustomField) {
        assertResCustomFieldAllUpdatablePropertiesEquals(expectedResCustomField, getPersistedResCustomField(expectedResCustomField));
    }
}
