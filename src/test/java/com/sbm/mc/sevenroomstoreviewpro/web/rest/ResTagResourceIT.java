package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import static com.sbm.mc.sevenroomstoreviewpro.domain.ResTagAsserts.*;
import static com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.mc.sevenroomstoreviewpro.IntegrationTest;
import com.sbm.mc.sevenroomstoreviewpro.domain.ResTag;
import com.sbm.mc.sevenroomstoreviewpro.repository.ResTagRepository;
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
 * Integration tests for the {@link ResTagResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResTagResourceIT {

    private static final String DEFAULT_TAG = "AAAAAAAAAA";
    private static final String UPDATED_TAG = "BBBBBBBBBB";

    private static final String DEFAULT_TAG_DISPLAY = "AAAAAAAAAA";
    private static final String UPDATED_TAG_DISPLAY = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_DISPLAY = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_DISPLAY = "BBBBBBBBBB";

    private static final String DEFAULT_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_COLOR = "BBBBBBBBBB";

    private static final String DEFAULT_TAG_SEARCH_QUERY = "AAAAAAAAAA";
    private static final String UPDATED_TAG_SEARCH_QUERY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/res-tags";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ResTagRepository resTagRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResTagMockMvc;

    private ResTag resTag;

    private ResTag insertedResTag;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResTag createEntity(EntityManager em) {
        ResTag resTag = new ResTag()
            .tag(DEFAULT_TAG)
            .tagDisplay(DEFAULT_TAG_DISPLAY)
            .group(DEFAULT_GROUP)
            .groupDisplay(DEFAULT_GROUP_DISPLAY)
            .color(DEFAULT_COLOR)
            .tagSearchQuery(DEFAULT_TAG_SEARCH_QUERY);
        return resTag;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResTag createUpdatedEntity(EntityManager em) {
        ResTag resTag = new ResTag()
            .tag(UPDATED_TAG)
            .tagDisplay(UPDATED_TAG_DISPLAY)
            .group(UPDATED_GROUP)
            .groupDisplay(UPDATED_GROUP_DISPLAY)
            .color(UPDATED_COLOR)
            .tagSearchQuery(UPDATED_TAG_SEARCH_QUERY);
        return resTag;
    }

    @BeforeEach
    public void initTest() {
        resTag = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedResTag != null) {
            resTagRepository.delete(insertedResTag);
            insertedResTag = null;
        }
    }

    @Test
    @Transactional
    void createResTag() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ResTag
        var returnedResTag = om.readValue(
            restResTagMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resTag)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ResTag.class
        );

        // Validate the ResTag in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertResTagUpdatableFieldsEquals(returnedResTag, getPersistedResTag(returnedResTag));

        insertedResTag = returnedResTag;
    }

    @Test
    @Transactional
    void createResTagWithExistingId() throws Exception {
        // Create the ResTag with an existing ID
        resTag.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restResTagMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resTag)))
            .andExpect(status().isBadRequest());

        // Validate the ResTag in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllResTags() throws Exception {
        // Initialize the database
        insertedResTag = resTagRepository.saveAndFlush(resTag);

        // Get all the resTagList
        restResTagMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resTag.getId().intValue())))
            .andExpect(jsonPath("$.[*].tag").value(hasItem(DEFAULT_TAG)))
            .andExpect(jsonPath("$.[*].tagDisplay").value(hasItem(DEFAULT_TAG_DISPLAY)))
            .andExpect(jsonPath("$.[*].group").value(hasItem(DEFAULT_GROUP)))
            .andExpect(jsonPath("$.[*].groupDisplay").value(hasItem(DEFAULT_GROUP_DISPLAY)))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR)))
            .andExpect(jsonPath("$.[*].tagSearchQuery").value(hasItem(DEFAULT_TAG_SEARCH_QUERY)));
    }

    @Test
    @Transactional
    void getResTag() throws Exception {
        // Initialize the database
        insertedResTag = resTagRepository.saveAndFlush(resTag);

        // Get the resTag
        restResTagMockMvc
            .perform(get(ENTITY_API_URL_ID, resTag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resTag.getId().intValue()))
            .andExpect(jsonPath("$.tag").value(DEFAULT_TAG))
            .andExpect(jsonPath("$.tagDisplay").value(DEFAULT_TAG_DISPLAY))
            .andExpect(jsonPath("$.group").value(DEFAULT_GROUP))
            .andExpect(jsonPath("$.groupDisplay").value(DEFAULT_GROUP_DISPLAY))
            .andExpect(jsonPath("$.color").value(DEFAULT_COLOR))
            .andExpect(jsonPath("$.tagSearchQuery").value(DEFAULT_TAG_SEARCH_QUERY));
    }

    @Test
    @Transactional
    void getNonExistingResTag() throws Exception {
        // Get the resTag
        restResTagMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingResTag() throws Exception {
        // Initialize the database
        insertedResTag = resTagRepository.saveAndFlush(resTag);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resTag
        ResTag updatedResTag = resTagRepository.findById(resTag.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedResTag are not directly saved in db
        em.detach(updatedResTag);
        updatedResTag
            .tag(UPDATED_TAG)
            .tagDisplay(UPDATED_TAG_DISPLAY)
            .group(UPDATED_GROUP)
            .groupDisplay(UPDATED_GROUP_DISPLAY)
            .color(UPDATED_COLOR)
            .tagSearchQuery(UPDATED_TAG_SEARCH_QUERY);

        restResTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedResTag.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedResTag))
            )
            .andExpect(status().isOk());

        // Validate the ResTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedResTagToMatchAllProperties(updatedResTag);
    }

    @Test
    @Transactional
    void putNonExistingResTag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resTag.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResTagMockMvc
            .perform(put(ENTITY_API_URL_ID, resTag.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resTag)))
            .andExpect(status().isBadRequest());

        // Validate the ResTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchResTag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resTag.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(resTag))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamResTag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resTag.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResTagMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resTag)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateResTagWithPatch() throws Exception {
        // Initialize the database
        insertedResTag = resTagRepository.saveAndFlush(resTag);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resTag using partial update
        ResTag partialUpdatedResTag = new ResTag();
        partialUpdatedResTag.setId(resTag.getId());

        partialUpdatedResTag.color(UPDATED_COLOR);

        restResTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedResTag))
            )
            .andExpect(status().isOk());

        // Validate the ResTag in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertResTagUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedResTag, resTag), getPersistedResTag(resTag));
    }

    @Test
    @Transactional
    void fullUpdateResTagWithPatch() throws Exception {
        // Initialize the database
        insertedResTag = resTagRepository.saveAndFlush(resTag);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resTag using partial update
        ResTag partialUpdatedResTag = new ResTag();
        partialUpdatedResTag.setId(resTag.getId());

        partialUpdatedResTag
            .tag(UPDATED_TAG)
            .tagDisplay(UPDATED_TAG_DISPLAY)
            .group(UPDATED_GROUP)
            .groupDisplay(UPDATED_GROUP_DISPLAY)
            .color(UPDATED_COLOR)
            .tagSearchQuery(UPDATED_TAG_SEARCH_QUERY);

        restResTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedResTag))
            )
            .andExpect(status().isOk());

        // Validate the ResTag in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertResTagUpdatableFieldsEquals(partialUpdatedResTag, getPersistedResTag(partialUpdatedResTag));
    }

    @Test
    @Transactional
    void patchNonExistingResTag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resTag.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, resTag.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(resTag))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchResTag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resTag.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(resTag))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamResTag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resTag.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResTagMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(resTag)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteResTag() throws Exception {
        // Initialize the database
        insertedResTag = resTagRepository.saveAndFlush(resTag);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the resTag
        restResTagMockMvc
            .perform(delete(ENTITY_API_URL_ID, resTag.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return resTagRepository.count();
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

    protected ResTag getPersistedResTag(ResTag resTag) {
        return resTagRepository.findById(resTag.getId()).orElseThrow();
    }

    protected void assertPersistedResTagToMatchAllProperties(ResTag expectedResTag) {
        assertResTagAllPropertiesEquals(expectedResTag, getPersistedResTag(expectedResTag));
    }

    protected void assertPersistedResTagToMatchUpdatableProperties(ResTag expectedResTag) {
        assertResTagAllUpdatablePropertiesEquals(expectedResTag, getPersistedResTag(expectedResTag));
    }
}
