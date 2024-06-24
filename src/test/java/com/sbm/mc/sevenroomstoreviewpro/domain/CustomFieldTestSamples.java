package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CustomFieldTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static CustomField getCustomFieldSample1() {
        return new CustomField().id(1L).systemName("systemName1").displayOrder(1).name("name1").value("value1");
    }

    public static CustomField getCustomFieldSample2() {
        return new CustomField().id(2L).systemName("systemName2").displayOrder(2).name("name2").value("value2");
    }

    public static CustomField getCustomFieldRandomSampleGenerator() {
        return new CustomField()
            .id(longCount.incrementAndGet())
            .systemName(UUID.randomUUID().toString())
            .displayOrder(intCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .value(UUID.randomUUID().toString());
    }
}
