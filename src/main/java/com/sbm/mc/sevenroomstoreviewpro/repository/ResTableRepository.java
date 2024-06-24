package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResTable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ResTable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResTableRepository extends JpaRepository<ResTable, Long> {}
