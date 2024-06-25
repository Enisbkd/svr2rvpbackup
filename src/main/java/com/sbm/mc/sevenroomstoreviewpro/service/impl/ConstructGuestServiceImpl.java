package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.sbm.mc.sevenroomstoreviewpro.domain.*;
import com.sbm.mc.sevenroomstoreviewpro.service.ConstructGuestService;
import com.sbm.mc.sevenroomstoreviewpro.service.SevenroomsService;
import java.util.HashMap;
import org.springframework.stereotype.Service;

@Service
public class ConstructGuestServiceImpl implements ConstructGuestService {

    private final SevenroomsService sevenroomsService;

    HashMap<String, String> Venues = Constants.VENUES;

    public ConstructGuestServiceImpl(SevenroomsService sevenroomsService) {
        this.sevenroomsService = sevenroomsService;
    }

    public RvpProfile constructGuest(ReservationPayload reservationPayload) {
        Reservation reservation = reservationPayload.getReservation();
        String client_id = reservation.getClientId();
        String clientPayload = sevenroomsService.getClientFromSevenRoomsApi(client_id);
        Client client = sevenroomsService.extractClientFields(clientPayload);
        RvpProfile profile = new RvpProfile();
        profile.setCheckin(reservation.getRealDateTimeOfSlot());
        profile.setFirstName(reservation.getFirstname());
        profile.setLastName(reservation.getLastname());
        profile.setEmail(reservation.getEmail());
        profile.setEmail2(client.getEmail());
        profile.setPmsId(Venues.get(reservation.getVenueId()));
        profile.setLanguage(client.getPreferredLanguageCode());

        return profile;
    }
}
