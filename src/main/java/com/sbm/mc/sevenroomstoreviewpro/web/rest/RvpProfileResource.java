package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import com.sbm.mc.sevenroomstoreviewpro.domain.RvpProfile;
import com.sbm.mc.sevenroomstoreviewpro.repository.RvpProfileRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.RvpProfileService;
import com.sbm.mc.sevenroomstoreviewpro.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.RvpProfile}.
 */
@RestController
@RequestMapping("/api/rvp-profiles")
public class RvpProfileResource {

    private final Logger log = LoggerFactory.getLogger(RvpProfileResource.class);

    private static final String ENTITY_NAME = "rvpProfile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RvpProfileService rvpProfileService;

    private final RvpProfileRepository rvpProfileRepository;

    public RvpProfileResource(RvpProfileService rvpProfileService, RvpProfileRepository rvpProfileRepository) {
        this.rvpProfileService = rvpProfileService;
        this.rvpProfileRepository = rvpProfileRepository;
    }

    /**
     * {@code POST  /rvp-profiles} : Create a new rvpProfile.
     *
     * @param rvpProfile the rvpProfile to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rvpProfile, or with status {@code 400 (Bad Request)} if the rvpProfile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<RvpProfile> createRvpProfile(@RequestBody RvpProfile rvpProfile) throws URISyntaxException {
        log.debug("REST request to save RvpProfile : {}", rvpProfile);
        if (rvpProfile.getId() != null) {
            throw new BadRequestAlertException("A new rvpProfile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        rvpProfile = rvpProfileService.save(rvpProfile);
        return ResponseEntity.created(new URI("/api/rvp-profiles/" + rvpProfile.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, rvpProfile.getId().toString()))
            .body(rvpProfile);
    }

    /**
     * {@code PUT  /rvp-profiles/:id} : Updates an existing rvpProfile.
     *
     * @param id the id of the rvpProfile to save.
     * @param rvpProfile the rvpProfile to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rvpProfile,
     * or with status {@code 400 (Bad Request)} if the rvpProfile is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rvpProfile couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RvpProfile> updateRvpProfile(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RvpProfile rvpProfile
    ) throws URISyntaxException {
        log.debug("REST request to update RvpProfile : {}, {}", id, rvpProfile);
        if (rvpProfile.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rvpProfile.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rvpProfileRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        rvpProfile = rvpProfileService.update(rvpProfile);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rvpProfile.getId().toString()))
            .body(rvpProfile);
    }

    /**
     * {@code PATCH  /rvp-profiles/:id} : Partial updates given fields of an existing rvpProfile, field will ignore if it is null
     *
     * @param id the id of the rvpProfile to save.
     * @param rvpProfile the rvpProfile to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rvpProfile,
     * or with status {@code 400 (Bad Request)} if the rvpProfile is not valid,
     * or with status {@code 404 (Not Found)} if the rvpProfile is not found,
     * or with status {@code 500 (Internal Server Error)} if the rvpProfile couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RvpProfile> partialUpdateRvpProfile(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RvpProfile rvpProfile
    ) throws URISyntaxException {
        log.debug("REST request to partial update RvpProfile partially : {}, {}", id, rvpProfile);
        if (rvpProfile.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rvpProfile.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rvpProfileRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RvpProfile> result = rvpProfileService.partialUpdate(rvpProfile);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rvpProfile.getId().toString())
        );
    }

    /**
     * {@code GET  /rvp-profiles} : get all the rvpProfiles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rvpProfiles in body.
     */
    @GetMapping("")
    public List<RvpProfile> getAllRvpProfiles() {
        log.debug("REST request to get all RvpProfiles");
        return rvpProfileService.findAll();
    }

    /**
     * {@code GET  /rvp-profiles/:id} : get the "id" rvpProfile.
     *
     * @param id the id of the rvpProfile to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rvpProfile, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RvpProfile> getRvpProfile(@PathVariable("id") Long id) {
        log.debug("REST request to get RvpProfile : {}", id);
        Optional<RvpProfile> rvpProfile = rvpProfileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rvpProfile);
    }

    /**
     * {@code DELETE  /rvp-profiles/:id} : delete the "id" rvpProfile.
     *
     * @param id the id of the rvpProfile to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRvpProfile(@PathVariable("id") Long id) {
        log.debug("REST request to delete RvpProfile : {}", id);
        rvpProfileService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
