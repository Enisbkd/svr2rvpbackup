package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import static com.sbm.mc.sevenroomstoreviewpro.domain.CustomFieldAsserts.*;
import static com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.mc.sevenroomstoreviewpro.IntegrationTest;
import com.sbm.mc.sevenroomstoreviewpro.domain.CustomField;
import com.sbm.mc.sevenroomstoreviewpro.repository.CustomFieldRepository;
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
 * Integration tests for the {@link CustomFieldResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CustomFieldResourceIT {

    private static final String DEFAULT_SYSTEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEM_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_DISPLAY_ORDER = 1;
    private static final Integer UPDATED_DISPLAY_ORDER = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/custom-fields";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CustomFieldRepository customFieldRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustomFieldMockMvc;

    private CustomField customField;

    private CustomField insertedCustomField;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomField createEntity(EntityManager em) {
        CustomField customField = new CustomField()
            .systemName(DEFAULT_SYSTEM_NAME)
            .displayOrder(DEFAULT_DISPLAY_ORDER)
            .name(DEFAULT_NAME)
            .value(DEFAULT_VALUE);
        return customField;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomField createUpdatedEntity(EntityManager em) {
        CustomField customField = new CustomField()
            .systemName(UPDATED_SYSTEM_NAME)
            .displayOrder(UPDATED_DISPLAY_ORDER)
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE);
        return customField;
    }

    @BeforeEach
    public void initTest() {
        customField = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedCustomField != null) {
            customFieldRepository.delete(insertedCustomField);
            insertedCustomField = null;
        }
    }

    @Test
    @Transactional
    void createCustomField() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CustomField
        var returnedCustomField = om.readValue(
            restCustomFieldMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customField)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CustomField.class
        );

        // Validate the CustomField in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCustomFieldUpdatableFieldsEquals(returnedCustomField, getPersistedCustomField(returnedCustomField));

        insertedCustomField = returnedCustomField;
    }

    @Test
    @Transactional
    void createCustomFieldWithExistingId() throws Exception {
        // Create the CustomField with an existing ID
        customField.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomFieldMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customField)))
            .andExpect(status().isBadRequest());

        // Validate the CustomField in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCustomFields() throws Exception {
        // Initialize the database
        insertedCustomField = customFieldRepository.saveAndFlush(customField);

        // Get all the customFieldList
        restCustomFieldMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customField.getId().intValue())))
            .andExpect(jsonPath("$.[*].systemName").value(hasItem(DEFAULT_SYSTEM_NAME)))
            .andExpect(jsonPath("$.[*].displayOrder").value(hasItem(DEFAULT_DISPLAY_ORDER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));
    }

    @Test
    @Transactional
    void getCustomField() throws Exception {
        // Initialize the database
        insertedCustomField = customFieldRepository.saveAndFlush(customField);

        // Get the customField
        restCustomFieldMockMvc
            .perform(get(ENTITY_API_URL_ID, customField.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(customField.getId().intValue()))
            .andExpect(jsonPath("$.systemName").value(DEFAULT_SYSTEM_NAME))
            .andExpect(jsonPath("$.displayOrder").value(DEFAULT_DISPLAY_ORDER))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE));
    }

    @Test
    @Transactional
    void getNonExistingCustomField() throws Exception {
        // Get the customField
        restCustomFieldMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCustomField() throws Exception {
        // Initialize the database
        insertedCustomField = customFieldRepository.saveAndFlush(customField);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customField
        CustomField updatedCustomField = customFieldRepository.findById(customField.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCustomField are not directly saved in db
        em.detach(updatedCustomField);
        updatedCustomField.systemName(UPDATED_SYSTEM_NAME).displayOrder(UPDATED_DISPLAY_ORDER).name(UPDATED_NAME).value(UPDATED_VALUE);

        restCustomFieldMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCustomField.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCustomField))
            )
            .andExpect(status().isOk());

        // Validate the CustomField in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCustomFieldToMatchAllProperties(updatedCustomField);
    }

    @Test
    @Transactional
    void putNonExistingCustomField() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customField.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomFieldMockMvc
            .perform(
                put(ENTITY_API_URL_ID, customField.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(customField))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomField in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCustomField() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customField.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomFieldMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(customField))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomField in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCustomField() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customField.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomFieldMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customField)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CustomField in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCustomFieldWithPatch() throws Exception {
        // Initialize the database
        insertedCustomField = customFieldRepository.saveAndFlush(customField);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customField using partial update
        CustomField partialUpdatedCustomField = new CustomField();
        partialUpdatedCustomField.setId(customField.getId());

        partialUpdatedCustomField.displayOrder(UPDATED_DISPLAY_ORDER).value(UPDATED_VALUE);

        restCustomFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomField.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCustomField))
            )
            .andExpect(status().isOk());

        // Validate the CustomField in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCustomFieldUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCustomField, customField),
            getPersistedCustomField(customField)
        );
    }

    @Test
    @Transactional
    void fullUpdateCustomFieldWithPatch() throws Exception {
        // Initialize the database
        insertedCustomField = customFieldRepository.saveAndFlush(customField);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customField using partial update
        CustomField partialUpdatedCustomField = new CustomField();
        partialUpdatedCustomField.setId(customField.getId());

        partialUpdatedCustomField
            .systemName(UPDATED_SYSTEM_NAME)
            .displayOrder(UPDATED_DISPLAY_ORDER)
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE);

        restCustomFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomField.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCustomField))
            )
            .andExpect(status().isOk());

        // Validate the CustomField in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCustomFieldUpdatableFieldsEquals(partialUpdatedCustomField, getPersistedCustomField(partialUpdatedCustomField));
    }

    @Test
    @Transactional
    void patchNonExistingCustomField() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customField.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, customField.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(customField))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomField in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCustomField() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customField.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomFieldMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(customField))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomField in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCustomField() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customField.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomFieldMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(customField)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CustomField in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCustomField() throws Exception {
        // Initialize the database
        insertedCustomField = customFieldRepository.saveAndFlush(customField);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the customField
        restCustomFieldMockMvc
            .perform(delete(ENTITY_API_URL_ID, customField.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return customFieldRepository.count();
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

    protected CustomField getPersistedCustomField(CustomField customField) {
        return customFieldRepository.findById(customField.getId()).orElseThrow();
    }

    protected void assertPersistedCustomFieldToMatchAllProperties(CustomField expectedCustomField) {
        assertCustomFieldAllPropertiesEquals(expectedCustomField, getPersistedCustomField(expectedCustomField));
    }

    protected void assertPersistedCustomFieldToMatchUpdatableProperties(CustomField expectedCustomField) {
        assertCustomFieldAllUpdatablePropertiesEquals(expectedCustomField, getPersistedCustomField(expectedCustomField));
    }
}
