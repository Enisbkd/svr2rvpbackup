package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.sbm.mc.sevenroomstoreviewpro.domain.*;
import com.sbm.mc.sevenroomstoreviewpro.service.ConstructGuestService;
import com.sbm.mc.sevenroomstoreviewpro.service.SevenroomsService;
import java.time.LocalDate;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConstructGuestServiceImpl implements ConstructGuestService {

    private final Logger logger = LoggerFactory.getLogger(ConstructGuestServiceImpl.class);

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
        profile.setCivilite(client.getSalutation());

        return profile;
    }

    public Boolean validateReservation(ReservationPayload reservationPayload) {
        Boolean validPayload = false;
        Reservation reservation = reservationPayload.getReservation();
        LocalDate currentDate = LocalDate.now();
        LocalDate oneWeekAgo = currentDate.minusWeeks(1);

        if (
            reservation.getDate().isAfter(oneWeekAgo) &&
            reservation.getStatus().equalsIgnoreCase("COMPLETE") &&
            !reservationPayload.getEvent_type().equalsIgnoreCase("deleted")
        ) {
            validPayload = true;
            logger.info(
                "Reservation will be processed. " +
                ", Date : " +
                reservationPayload.getReservation().getDate() +
                ", Status : " +
                reservationPayload.getReservation().getStatus() +
                ", Event Type : " +
                reservationPayload.getEvent_type()
            );
        } else {
            logger.info(
                "Reservation won't be processed. " +
                ", Date : " +
                reservationPayload.getReservation().getDate() +
                ", Status : " +
                reservationPayload.getReservation().getStatus() +
                ", Event Type : " +
                reservationPayload.getEvent_type()
            );
        }
        return validPayload;
    }
}
