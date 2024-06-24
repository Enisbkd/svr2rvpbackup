package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResPosticketsItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ResPosticketsItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResPosticketsItemRepository extends JpaRepository<ResPosticketsItem, Long> {}
