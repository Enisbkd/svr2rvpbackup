package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ResPosticketsItemTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ResPosticketsItem getResPosticketsItemSample1() {
        return new ResPosticketsItem().id(1L).name("name1").quantity(1);
    }

    public static ResPosticketsItem getResPosticketsItemSample2() {
        return new ResPosticketsItem().id(2L).name("name2").quantity(2);
    }

    public static ResPosticketsItem getResPosticketsItemRandomSampleGenerator() {
        return new ResPosticketsItem()
            .id(longCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .quantity(intCount.incrementAndGet());
    }
}
