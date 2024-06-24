package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.RvpProfile;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RvpProfile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RvpProfileRepository extends JpaRepository<RvpProfile, Long> {}
