package com.sbm.mc.sevenroomstoreviewpro.service;

import com.sbm.mc.sevenroomstoreviewpro.domain.RvpProfile;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.RvpProfile}.
 */
public interface RvpProfileService {
    /**
     * Save a rvpProfile.
     *
     * @param rvpProfile the entity to save.
     * @return the persisted entity.
     */
    RvpProfile save(RvpProfile rvpProfile);

    /**
     * Updates a rvpProfile.
     *
     * @param rvpProfile the entity to update.
     * @return the persisted entity.
     */
    RvpProfile update(RvpProfile rvpProfile);

    /**
     * Partially updates a rvpProfile.
     *
     * @param rvpProfile the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RvpProfile> partialUpdate(RvpProfile rvpProfile);

    /**
     * Get all the rvpProfiles.
     *
     * @return the list of entities.
     */
    List<RvpProfile> findAll();

    /**
     * Get the "id" rvpProfile.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RvpProfile> findOne(Long id);

    /**
     * Delete the "id" rvpProfile.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
