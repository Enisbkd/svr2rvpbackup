package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class RvpProfileTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static RvpProfile getRvpProfileSample1() {
        return new RvpProfile().id(1L).pmsId("pmsId1").firstName("firstName1").lastName("lastName1").language("language1").email("email1");
    }

    public static RvpProfile getRvpProfileSample2() {
        return new RvpProfile().id(2L).pmsId("pmsId2").firstName("firstName2").lastName("lastName2").language("language2").email("email2");
    }

    public static RvpProfile getRvpProfileRandomSampleGenerator() {
        return new RvpProfile()
            .id(longCount.incrementAndGet())
            .pmsId(UUID.randomUUID().toString())
            .firstName(UUID.randomUUID().toString())
            .lastName(UUID.randomUUID().toString())
            .language(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString());
    }
}
