package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.BookingName;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the BookingName entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookingNameRepository extends JpaRepository<BookingName, Long> {}
