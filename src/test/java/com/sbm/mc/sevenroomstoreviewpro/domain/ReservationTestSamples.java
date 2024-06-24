package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ReservationTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Reservation getReservationSample1() {
        return new Reservation()
            .id(1L)
            .resvId("resvId1")
            .created("created1")
            .updated("updated1")
            .deleted("deleted1")
            .venueGroupClientId("venueGroupClientId1")
            .venueGroupId("venueGroupId1")
            .venueId("venueId1")
            .date("date1")
            .duration(1)
            .checkNumbers("checkNumbers1")
            .shiftCategory("shiftCategory1")
            .shiftPersistentId("shiftPersistentId1")
            .maxGuests(1)
            .mfratioMale(1)
            .mfratioFemale(1)
            .status("status1")
            .statusDisplay("statusDisplay1")
            .statusSimple("statusSimple1")
            .accessPersistentId("accessPersistentId1")
            .arrivedGuests(1)
            .bookedby("bookedby1")
            .clientReferenceCode("clientReferenceCode1")
            .lastname("lastname1")
            .firstname("firstname1")
            .email("email1")
            .phoneNumber("phoneNumber1")
            .address("address1")
            .address2("address21")
            .city("city1")
            .postalCode("postalCode1")
            .state("state1")
            .country("country1")
            .loyaltyId("loyaltyId1")
            .loyaltyRank(1)
            .loyaltyTier("loyaltyTier1")
            .notes("notes1")
            .arrivalTime("arrivalTime1")
            .seatedTime("seatedTime1")
            .leftTime("leftTime1")
            .clientRequests("clientRequests1")
            .comps(1)
            .compsPriceType("compsPriceType1")
            .costOption(1)
            .policy("policy1")
            .minPrice(1)
            .totalPayment(1)
            .paidBy("paidBy1")
            .servedBy("servedBy1")
            .rating(1)
            .problems("problems1")
            .autoAssignments("autoAssignments1")
            .externalClientId("externalClientId1")
            .externalId("externalId1")
            .externalReferenceCode("externalReferenceCode1")
            .externalUserId("externalUserId1")
            .modifyReservationLink("modifyReservationLink1")
            .referenceCode("referenceCode1")
            .reservationType("reservationType1")
            .sourceClientId("sourceClientId1")
            .userId("userId1")
            .userName("userName1");
    }

    public static Reservation getReservationSample2() {
        return new Reservation()
            .id(2L)
            .resvId("resvId2")
            .created("created2")
            .updated("updated2")
            .deleted("deleted2")
            .venueGroupClientId("venueGroupClientId2")
            .venueGroupId("venueGroupId2")
            .venueId("venueId2")
            .date("date2")
            .duration(2)
            .checkNumbers("checkNumbers2")
            .shiftCategory("shiftCategory2")
            .shiftPersistentId("shiftPersistentId2")
            .maxGuests(2)
            .mfratioMale(2)
            .mfratioFemale(2)
            .status("status2")
            .statusDisplay("statusDisplay2")
            .statusSimple("statusSimple2")
            .accessPersistentId("accessPersistentId2")
            .arrivedGuests(2)
            .bookedby("bookedby2")
            .clientReferenceCode("clientReferenceCode2")
            .lastname("lastname2")
            .firstname("firstname2")
            .email("email2")
            .phoneNumber("phoneNumber2")
            .address("address2")
            .address2("address22")
            .city("city2")
            .postalCode("postalCode2")
            .state("state2")
            .country("country2")
            .loyaltyId("loyaltyId2")
            .loyaltyRank(2)
            .loyaltyTier("loyaltyTier2")
            .notes("notes2")
            .arrivalTime("arrivalTime2")
            .seatedTime("seatedTime2")
            .leftTime("leftTime2")
            .clientRequests("clientRequests2")
            .comps(2)
            .compsPriceType("compsPriceType2")
            .costOption(2)
            .policy("policy2")
            .minPrice(2)
            .totalPayment(2)
            .paidBy("paidBy2")
            .servedBy("servedBy2")
            .rating(2)
            .problems("problems2")
            .autoAssignments("autoAssignments2")
            .externalClientId("externalClientId2")
            .externalId("externalId2")
            .externalReferenceCode("externalReferenceCode2")
            .externalUserId("externalUserId2")
            .modifyReservationLink("modifyReservationLink2")
            .referenceCode("referenceCode2")
            .reservationType("reservationType2")
            .sourceClientId("sourceClientId2")
            .userId("userId2")
            .userName("userName2");
    }

    public static Reservation getReservationRandomSampleGenerator() {
        return new Reservation()
            .id(longCount.incrementAndGet())
            .resvId(UUID.randomUUID().toString())
            .created(UUID.randomUUID().toString())
            .updated(UUID.randomUUID().toString())
            .deleted(UUID.randomUUID().toString())
            .venueGroupClientId(UUID.randomUUID().toString())
            .venueGroupId(UUID.randomUUID().toString())
            .venueId(UUID.randomUUID().toString())
            .date(UUID.randomUUID().toString())
            .duration(intCount.incrementAndGet())
            .checkNumbers(UUID.randomUUID().toString())
            .shiftCategory(UUID.randomUUID().toString())
            .shiftPersistentId(UUID.randomUUID().toString())
            .maxGuests(intCount.incrementAndGet())
            .mfratioMale(intCount.incrementAndGet())
            .mfratioFemale(intCount.incrementAndGet())
            .status(UUID.randomUUID().toString())
            .statusDisplay(UUID.randomUUID().toString())
            .statusSimple(UUID.randomUUID().toString())
            .accessPersistentId(UUID.randomUUID().toString())
            .arrivedGuests(intCount.incrementAndGet())
            .bookedby(UUID.randomUUID().toString())
            .clientReferenceCode(UUID.randomUUID().toString())
            .lastname(UUID.randomUUID().toString())
            .firstname(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .phoneNumber(UUID.randomUUID().toString())
            .address(UUID.randomUUID().toString())
            .address2(UUID.randomUUID().toString())
            .city(UUID.randomUUID().toString())
            .postalCode(UUID.randomUUID().toString())
            .state(UUID.randomUUID().toString())
            .country(UUID.randomUUID().toString())
            .loyaltyId(UUID.randomUUID().toString())
            .loyaltyRank(intCount.incrementAndGet())
            .loyaltyTier(UUID.randomUUID().toString())
            .notes(UUID.randomUUID().toString())
            .arrivalTime(UUID.randomUUID().toString())
            .seatedTime(UUID.randomUUID().toString())
            .leftTime(UUID.randomUUID().toString())
            .clientRequests(UUID.randomUUID().toString())
            .comps(intCount.incrementAndGet())
            .compsPriceType(UUID.randomUUID().toString())
            .costOption(intCount.incrementAndGet())
            .policy(UUID.randomUUID().toString())
            .minPrice(intCount.incrementAndGet())
            .totalPayment(intCount.incrementAndGet())
            .paidBy(UUID.randomUUID().toString())
            .servedBy(UUID.randomUUID().toString())
            .rating(intCount.incrementAndGet())
            .problems(UUID.randomUUID().toString())
            .autoAssignments(UUID.randomUUID().toString())
            .externalClientId(UUID.randomUUID().toString())
            .externalId(UUID.randomUUID().toString())
            .externalReferenceCode(UUID.randomUUID().toString())
            .externalUserId(UUID.randomUUID().toString())
            .modifyReservationLink(UUID.randomUUID().toString())
            .referenceCode(UUID.randomUUID().toString())
            .reservationType(UUID.randomUUID().toString())
            .sourceClientId(UUID.randomUUID().toString())
            .userId(UUID.randomUUID().toString())
            .userName(UUID.randomUUID().toString());
    }
}
