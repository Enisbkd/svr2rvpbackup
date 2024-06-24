package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ResTableTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ResTable getResTableSample1() {
        return new ResTable().id(1L).tableNumber("tableNumber1");
    }

    public static ResTable getResTableSample2() {
        return new ResTable().id(2L).tableNumber("tableNumber2");
    }

    public static ResTable getResTableRandomSampleGenerator() {
        return new ResTable().id(longCount.incrementAndGet()).tableNumber(UUID.randomUUID().toString());
    }
}
