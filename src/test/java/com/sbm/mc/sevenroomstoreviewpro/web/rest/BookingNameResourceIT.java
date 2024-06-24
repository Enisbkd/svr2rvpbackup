package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import static com.sbm.mc.sevenroomstoreviewpro.domain.BookingNameAsserts.*;
import static com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.mc.sevenroomstoreviewpro.IntegrationTest;
import com.sbm.mc.sevenroomstoreviewpro.domain.BookingName;
import com.sbm.mc.sevenroomstoreviewpro.repository.BookingNameRepository;
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
 * Integration tests for the {@link BookingNameResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BookingNameResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/booking-names";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private BookingNameRepository bookingNameRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBookingNameMockMvc;

    private BookingName bookingName;

    private BookingName insertedBookingName;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BookingName createEntity(EntityManager em) {
        BookingName bookingName = new BookingName().name(DEFAULT_NAME);
        return bookingName;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BookingName createUpdatedEntity(EntityManager em) {
        BookingName bookingName = new BookingName().name(UPDATED_NAME);
        return bookingName;
    }

    @BeforeEach
    public void initTest() {
        bookingName = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedBookingName != null) {
            bookingNameRepository.delete(insertedBookingName);
            insertedBookingName = null;
        }
    }

    @Test
    @Transactional
    void createBookingName() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the BookingName
        var returnedBookingName = om.readValue(
            restBookingNameMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bookingName)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            BookingName.class
        );

        // Validate the BookingName in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertBookingNameUpdatableFieldsEquals(returnedBookingName, getPersistedBookingName(returnedBookingName));

        insertedBookingName = returnedBookingName;
    }

    @Test
    @Transactional
    void createBookingNameWithExistingId() throws Exception {
        // Create the BookingName with an existing ID
        bookingName.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBookingNameMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bookingName)))
            .andExpect(status().isBadRequest());

        // Validate the BookingName in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBookingNames() throws Exception {
        // Initialize the database
        insertedBookingName = bookingNameRepository.saveAndFlush(bookingName);

        // Get all the bookingNameList
        restBookingNameMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bookingName.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getBookingName() throws Exception {
        // Initialize the database
        insertedBookingName = bookingNameRepository.saveAndFlush(bookingName);

        // Get the bookingName
        restBookingNameMockMvc
            .perform(get(ENTITY_API_URL_ID, bookingName.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bookingName.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingBookingName() throws Exception {
        // Get the bookingName
        restBookingNameMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBookingName() throws Exception {
        // Initialize the database
        insertedBookingName = bookingNameRepository.saveAndFlush(bookingName);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the bookingName
        BookingName updatedBookingName = bookingNameRepository.findById(bookingName.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedBookingName are not directly saved in db
        em.detach(updatedBookingName);
        updatedBookingName.name(UPDATED_NAME);

        restBookingNameMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBookingName.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedBookingName))
            )
            .andExpect(status().isOk());

        // Validate the BookingName in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedBookingNameToMatchAllProperties(updatedBookingName);
    }

    @Test
    @Transactional
    void putNonExistingBookingName() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bookingName.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBookingNameMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bookingName.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(bookingName))
            )
            .andExpect(status().isBadRequest());

        // Validate the BookingName in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBookingName() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bookingName.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBookingNameMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(bookingName))
            )
            .andExpect(status().isBadRequest());

        // Validate the BookingName in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBookingName() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bookingName.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBookingNameMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bookingName)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BookingName in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBookingNameWithPatch() throws Exception {
        // Initialize the database
        insertedBookingName = bookingNameRepository.saveAndFlush(bookingName);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the bookingName using partial update
        BookingName partialUpdatedBookingName = new BookingName();
        partialUpdatedBookingName.setId(bookingName.getId());

        restBookingNameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBookingName.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBookingName))
            )
            .andExpect(status().isOk());

        // Validate the BookingName in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBookingNameUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedBookingName, bookingName),
            getPersistedBookingName(bookingName)
        );
    }

    @Test
    @Transactional
    void fullUpdateBookingNameWithPatch() throws Exception {
        // Initialize the database
        insertedBookingName = bookingNameRepository.saveAndFlush(bookingName);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the bookingName using partial update
        BookingName partialUpdatedBookingName = new BookingName();
        partialUpdatedBookingName.setId(bookingName.getId());

        partialUpdatedBookingName.name(UPDATED_NAME);

        restBookingNameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBookingName.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBookingName))
            )
            .andExpect(status().isOk());

        // Validate the BookingName in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBookingNameUpdatableFieldsEquals(partialUpdatedBookingName, getPersistedBookingName(partialUpdatedBookingName));
    }

    @Test
    @Transactional
    void patchNonExistingBookingName() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bookingName.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBookingNameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bookingName.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(bookingName))
            )
            .andExpect(status().isBadRequest());

        // Validate the BookingName in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBookingName() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bookingName.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBookingNameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(bookingName))
            )
            .andExpect(status().isBadRequest());

        // Validate the BookingName in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBookingName() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bookingName.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBookingNameMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(bookingName)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BookingName in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBookingName() throws Exception {
        // Initialize the database
        insertedBookingName = bookingNameRepository.saveAndFlush(bookingName);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the bookingName
        restBookingNameMockMvc
            .perform(delete(ENTITY_API_URL_ID, bookingName.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return bookingNameRepository.count();
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

    protected BookingName getPersistedBookingName(BookingName bookingName) {
        return bookingNameRepository.findById(bookingName.getId()).orElseThrow();
    }

    protected void assertPersistedBookingNameToMatchAllProperties(BookingName expectedBookingName) {
        assertBookingNameAllPropertiesEquals(expectedBookingName, getPersistedBookingName(expectedBookingName));
    }

    protected void assertPersistedBookingNameToMatchUpdatableProperties(BookingName expectedBookingName) {
        assertBookingNameAllUpdatablePropertiesEquals(expectedBookingName, getPersistedBookingName(expectedBookingName));
    }
}
