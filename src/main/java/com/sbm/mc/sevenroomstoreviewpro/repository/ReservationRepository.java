package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.Reservation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Reservation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {}
