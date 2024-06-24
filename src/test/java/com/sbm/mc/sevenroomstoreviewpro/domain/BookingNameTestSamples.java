package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class BookingNameTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static BookingName getBookingNameSample1() {
        return new BookingName().id(1L).name("name1");
    }

    public static BookingName getBookingNameSample2() {
        return new BookingName().id(2L).name("name2");
    }

    public static BookingName getBookingNameRandomSampleGenerator() {
        return new BookingName().id(longCount.incrementAndGet()).name(UUID.randomUUID().toString());
    }
}
