package com.sbm.mc.sevenroomstoreviewpro.service;

import com.sbm.mc.sevenroomstoreviewpro.domain.ReservationPayload;
import com.sbm.mc.sevenroomstoreviewpro.domain.RvpProfile;

public interface ConstructGuestService {
    RvpProfile constructGuest(ReservationPayload reservationPayload);
}
