package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResPosTicket;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ResPosTicket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResPosTicketRepository extends JpaRepository<ResPosTicket, Long> {}
