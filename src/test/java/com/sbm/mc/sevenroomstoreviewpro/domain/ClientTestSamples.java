package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ClientTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Client getClientSample1() {
        return new Client()
            .id(1L)
            .clientId("clientId1")
            .createdDate("createdDate1")
            .updatedDate("updatedDate1")
            .deletedDate("deletedDate1")
            .lastname("lastname1")
            .firstname("firstname1")
            .gender("gender1")
            .salutation("salutation1")
            .title("title1")
            .birthdayDay(1)
            .birthdayMonth(1)
            .birthdayAltMonth(1)
            .anniversaryDay(1)
            .anniversaryMonth(1)
            .company("company1")
            .email("email1")
            .emailAlt("emailAlt1")
            .phoneNumber("phoneNumber1")
            .phoneNumberlocale("phoneNumberlocale1")
            .phoneNumberalt("phoneNumberalt1")
            .phoneNumberaltlocale("phoneNumberaltlocale1")
            .address("address1")
            .address2("address21")
            .city("city1")
            .postalCode("postalCode1")
            .state("state1")
            .country("country1")
            .status("status1")
            .loyaltyId("loyaltyId1")
            .loyaltyRank(1)
            .loyaltyTier("loyaltyTier1")
            .marketingOptints("marketingOptints1")
            .marketingOptOutts("marketingOptOutts1")
            .notes("notes1")
            .privateNotes("privateNotes1")
            .tags("tags1")
            .referenceCode("referenceCode1")
            .externalUserId("externalUserId1")
            .venueGroupId("venueGroupId1")
            .birthdayAltDay(1)
            .userId("userId1")
            .userName("userName1")
            .totalOrderCount(1)
            .preferredLanguageCode("preferredLanguageCode1");
    }

    public static Client getClientSample2() {
        return new Client()
            .id(2L)
            .clientId("clientId2")
            .createdDate("createdDate2")
            .updatedDate("updatedDate2")
            .deletedDate("deletedDate2")
            .lastname("lastname2")
            .firstname("firstname2")
            .gender("gender2")
            .salutation("salutation2")
            .title("title2")
            .birthdayDay(2)
            .birthdayMonth(2)
            .birthdayAltMonth(2)
            .anniversaryDay(2)
            .anniversaryMonth(2)
            .company("company2")
            .email("email2")
            .emailAlt("emailAlt2")
            .phoneNumber("phoneNumber2")
            .phoneNumberlocale("phoneNumberlocale2")
            .phoneNumberalt("phoneNumberalt2")
            .phoneNumberaltlocale("phoneNumberaltlocale2")
            .address("address2")
            .address2("address22")
            .city("city2")
            .postalCode("postalCode2")
            .state("state2")
            .country("country2")
            .status("status2")
            .loyaltyId("loyaltyId2")
            .loyaltyRank(2)
            .loyaltyTier("loyaltyTier2")
            .marketingOptints("marketingOptints2")
            .marketingOptOutts("marketingOptOutts2")
            .notes("notes2")
            .privateNotes("privateNotes2")
            .tags("tags2")
            .referenceCode("referenceCode2")
            .externalUserId("externalUserId2")
            .venueGroupId("venueGroupId2")
            .birthdayAltDay(2)
            .userId("userId2")
            .userName("userName2")
            .totalOrderCount(2)
            .preferredLanguageCode("preferredLanguageCode2");
    }

    public static Client getClientRandomSampleGenerator() {
        return new Client()
            .id(longCount.incrementAndGet())
            .clientId(UUID.randomUUID().toString())
            .createdDate(UUID.randomUUID().toString())
            .updatedDate(UUID.randomUUID().toString())
            .deletedDate(UUID.randomUUID().toString())
            .lastname(UUID.randomUUID().toString())
            .firstname(UUID.randomUUID().toString())
            .gender(UUID.randomUUID().toString())
            .salutation(UUID.randomUUID().toString())
            .title(UUID.randomUUID().toString())
            .birthdayDay(intCount.incrementAndGet())
            .birthdayMonth(intCount.incrementAndGet())
            .birthdayAltMonth(intCount.incrementAndGet())
            .anniversaryDay(intCount.incrementAndGet())
            .anniversaryMonth(intCount.incrementAndGet())
            .company(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .emailAlt(UUID.randomUUID().toString())
            .phoneNumber(UUID.randomUUID().toString())
            .phoneNumberlocale(UUID.randomUUID().toString())
            .phoneNumberalt(UUID.randomUUID().toString())
            .phoneNumberaltlocale(UUID.randomUUID().toString())
            .address(UUID.randomUUID().toString())
            .address2(UUID.randomUUID().toString())
            .city(UUID.randomUUID().toString())
            .postalCode(UUID.randomUUID().toString())
            .state(UUID.randomUUID().toString())
            .country(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString())
            .loyaltyId(UUID.randomUUID().toString())
            .loyaltyRank(intCount.incrementAndGet())
            .loyaltyTier(UUID.randomUUID().toString())
            .marketingOptints(UUID.randomUUID().toString())
            .marketingOptOutts(UUID.randomUUID().toString())
            .notes(UUID.randomUUID().toString())
            .privateNotes(UUID.randomUUID().toString())
            .tags(UUID.randomUUID().toString())
            .referenceCode(UUID.randomUUID().toString())
            .externalUserId(UUID.randomUUID().toString())
            .venueGroupId(UUID.randomUUID().toString())
            .birthdayAltDay(intCount.incrementAndGet())
            .userId(UUID.randomUUID().toString())
            .userName(UUID.randomUUID().toString())
            .totalOrderCount(intCount.incrementAndGet())
            .preferredLanguageCode(UUID.randomUUID().toString());
    }
}
