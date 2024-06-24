package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.sbm.mc.sevenroomstoreviewpro.domain.RvpProfile;
import com.sbm.mc.sevenroomstoreviewpro.repository.RvpProfileRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.RvpProfileService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.RvpProfile}.
 */
@Service
@Transactional
public class RvpProfileServiceImpl implements RvpProfileService {

    private final Logger log = LoggerFactory.getLogger(RvpProfileServiceImpl.class);

    private final RvpProfileRepository rvpProfileRepository;

    public RvpProfileServiceImpl(RvpProfileRepository rvpProfileRepository) {
        this.rvpProfileRepository = rvpProfileRepository;
    }

    @Override
    public RvpProfile save(RvpProfile rvpProfile) {
        log.debug("Request to save RvpProfile : {}", rvpProfile);
        return rvpProfileRepository.save(rvpProfile);
    }

    @Override
    public RvpProfile update(RvpProfile rvpProfile) {
        log.debug("Request to update RvpProfile : {}", rvpProfile);
        return rvpProfileRepository.save(rvpProfile);
    }

    @Override
    public Optional<RvpProfile> partialUpdate(RvpProfile rvpProfile) {
        log.debug("Request to partially update RvpProfile : {}", rvpProfile);

        return rvpProfileRepository
            .findById(rvpProfile.getId())
            .map(existingRvpProfile -> {
                if (rvpProfile.getPmsId() != null) {
                    existingRvpProfile.setPmsId(rvpProfile.getPmsId());
                }
                if (rvpProfile.getFirstName() != null) {
                    existingRvpProfile.setFirstName(rvpProfile.getFirstName());
                }
                if (rvpProfile.getLastName() != null) {
                    existingRvpProfile.setLastName(rvpProfile.getLastName());
                }
                if (rvpProfile.getLanguage() != null) {
                    existingRvpProfile.setLanguage(rvpProfile.getLanguage());
                }
                if (rvpProfile.getCheckin() != null) {
                    existingRvpProfile.setCheckin(rvpProfile.getCheckin());
                }
                if (rvpProfile.getCheckout() != null) {
                    existingRvpProfile.setCheckout(rvpProfile.getCheckout());
                }
                if (rvpProfile.getEmail() != null) {
                    existingRvpProfile.setEmail(rvpProfile.getEmail());
                }

                return existingRvpProfile;
            })
            .map(rvpProfileRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RvpProfile> findAll() {
        log.debug("Request to get all RvpProfiles");
        return rvpProfileRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RvpProfile> findOne(Long id) {
        log.debug("Request to get RvpProfile : {}", id);
        return rvpProfileRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RvpProfile : {}", id);
        rvpProfileRepository.deleteById(id);
    }
}
