package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.ClientTag;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ClientTag entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientTagRepository extends JpaRepository<ClientTag, Long> {}
