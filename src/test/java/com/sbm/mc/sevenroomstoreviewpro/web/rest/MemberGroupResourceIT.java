package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import static com.sbm.mc.sevenroomstoreviewpro.domain.MemberGroupAsserts.*;
import static com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.mc.sevenroomstoreviewpro.IntegrationTest;
import com.sbm.mc.sevenroomstoreviewpro.domain.MemberGroup;
import com.sbm.mc.sevenroomstoreviewpro.repository.MemberGroupRepository;
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
 * Integration tests for the {@link MemberGroupResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MemberGroupResourceIT {

    private static final String ENTITY_API_URL = "/api/member-groups";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MemberGroupRepository memberGroupRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMemberGroupMockMvc;

    private MemberGroup memberGroup;

    private MemberGroup insertedMemberGroup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MemberGroup createEntity(EntityManager em) {
        MemberGroup memberGroup = new MemberGroup();
        return memberGroup;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MemberGroup createUpdatedEntity(EntityManager em) {
        MemberGroup memberGroup = new MemberGroup();
        return memberGroup;
    }

    @BeforeEach
    public void initTest() {
        memberGroup = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedMemberGroup != null) {
            memberGroupRepository.delete(insertedMemberGroup);
            insertedMemberGroup = null;
        }
    }

    @Test
    @Transactional
    void createMemberGroup() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the MemberGroup
        var returnedMemberGroup = om.readValue(
            restMemberGroupMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(memberGroup)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            MemberGroup.class
        );

        // Validate the MemberGroup in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertMemberGroupUpdatableFieldsEquals(returnedMemberGroup, getPersistedMemberGroup(returnedMemberGroup));

        insertedMemberGroup = returnedMemberGroup;
    }

    @Test
    @Transactional
    void createMemberGroupWithExistingId() throws Exception {
        // Create the MemberGroup with an existing ID
        memberGroup.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMemberGroupMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(memberGroup)))
            .andExpect(status().isBadRequest());

        // Validate the MemberGroup in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMemberGroups() throws Exception {
        // Initialize the database
        insertedMemberGroup = memberGroupRepository.saveAndFlush(memberGroup);

        // Get all the memberGroupList
        restMemberGroupMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(memberGroup.getId().intValue())));
    }

    @Test
    @Transactional
    void getMemberGroup() throws Exception {
        // Initialize the database
        insertedMemberGroup = memberGroupRepository.saveAndFlush(memberGroup);

        // Get the memberGroup
        restMemberGroupMockMvc
            .perform(get(ENTITY_API_URL_ID, memberGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(memberGroup.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingMemberGroup() throws Exception {
        // Get the memberGroup
        restMemberGroupMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMemberGroup() throws Exception {
        // Initialize the database
        insertedMemberGroup = memberGroupRepository.saveAndFlush(memberGroup);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the memberGroup
        MemberGroup updatedMemberGroup = memberGroupRepository.findById(memberGroup.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedMemberGroup are not directly saved in db
        em.detach(updatedMemberGroup);

        restMemberGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMemberGroup.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedMemberGroup))
            )
            .andExpect(status().isOk());

        // Validate the MemberGroup in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedMemberGroupToMatchAllProperties(updatedMemberGroup);
    }

    @Test
    @Transactional
    void putNonExistingMemberGroup() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        memberGroup.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMemberGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, memberGroup.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(memberGroup))
            )
            .andExpect(status().isBadRequest());

        // Validate the MemberGroup in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMemberGroup() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        memberGroup.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMemberGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(memberGroup))
            )
            .andExpect(status().isBadRequest());

        // Validate the MemberGroup in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMemberGroup() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        memberGroup.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMemberGroupMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(memberGroup)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MemberGroup in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMemberGroupWithPatch() throws Exception {
        // Initialize the database
        insertedMemberGroup = memberGroupRepository.saveAndFlush(memberGroup);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the memberGroup using partial update
        MemberGroup partialUpdatedMemberGroup = new MemberGroup();
        partialUpdatedMemberGroup.setId(memberGroup.getId());

        restMemberGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMemberGroup.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMemberGroup))
            )
            .andExpect(status().isOk());

        // Validate the MemberGroup in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMemberGroupUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedMemberGroup, memberGroup),
            getPersistedMemberGroup(memberGroup)
        );
    }

    @Test
    @Transactional
    void fullUpdateMemberGroupWithPatch() throws Exception {
        // Initialize the database
        insertedMemberGroup = memberGroupRepository.saveAndFlush(memberGroup);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the memberGroup using partial update
        MemberGroup partialUpdatedMemberGroup = new MemberGroup();
        partialUpdatedMemberGroup.setId(memberGroup.getId());

        restMemberGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMemberGroup.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMemberGroup))
            )
            .andExpect(status().isOk());

        // Validate the MemberGroup in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMemberGroupUpdatableFieldsEquals(partialUpdatedMemberGroup, getPersistedMemberGroup(partialUpdatedMemberGroup));
    }

    @Test
    @Transactional
    void patchNonExistingMemberGroup() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        memberGroup.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMemberGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, memberGroup.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(memberGroup))
            )
            .andExpect(status().isBadRequest());

        // Validate the MemberGroup in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMemberGroup() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        memberGroup.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMemberGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(memberGroup))
            )
            .andExpect(status().isBadRequest());

        // Validate the MemberGroup in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMemberGroup() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        memberGroup.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMemberGroupMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(memberGroup)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MemberGroup in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMemberGroup() throws Exception {
        // Initialize the database
        insertedMemberGroup = memberGroupRepository.saveAndFlush(memberGroup);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the memberGroup
        restMemberGroupMockMvc
            .perform(delete(ENTITY_API_URL_ID, memberGroup.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return memberGroupRepository.count();
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

    protected MemberGroup getPersistedMemberGroup(MemberGroup memberGroup) {
        return memberGroupRepository.findById(memberGroup.getId()).orElseThrow();
    }

    protected void assertPersistedMemberGroupToMatchAllProperties(MemberGroup expectedMemberGroup) {
        assertMemberGroupAllPropertiesEquals(expectedMemberGroup, getPersistedMemberGroup(expectedMemberGroup));
    }

    protected void assertPersistedMemberGroupToMatchUpdatableProperties(MemberGroup expectedMemberGroup) {
        assertMemberGroupAllUpdatablePropertiesEquals(expectedMemberGroup, getPersistedMemberGroup(expectedMemberGroup));
    }
}
