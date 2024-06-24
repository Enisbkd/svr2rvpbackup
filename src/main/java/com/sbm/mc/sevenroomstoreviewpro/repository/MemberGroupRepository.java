package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.MemberGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MemberGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MemberGroupRepository extends JpaRepository<MemberGroup, Long> {}
