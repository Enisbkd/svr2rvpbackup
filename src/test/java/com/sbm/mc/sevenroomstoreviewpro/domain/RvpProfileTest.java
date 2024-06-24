package com.sbm.mc.sevenroomstoreviewpro.domain;

import static com.sbm.mc.sevenroomstoreviewpro.domain.RvpProfileTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RvpProfileTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RvpProfile.class);
        RvpProfile rvpProfile1 = getRvpProfileSample1();
        RvpProfile rvpProfile2 = new RvpProfile();
        assertThat(rvpProfile1).isNotEqualTo(rvpProfile2);

        rvpProfile2.setId(rvpProfile1.getId());
        assertThat(rvpProfile1).isEqualTo(rvpProfile2);

        rvpProfile2 = getRvpProfileSample2();
        assertThat(rvpProfile1).isNotEqualTo(rvpProfile2);
    }
}
