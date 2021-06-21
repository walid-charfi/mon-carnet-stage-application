package com.fm.mcs.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.fm.mcs.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StageRadiologieTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StageRadiologie.class);
        StageRadiologie stageRadiologie1 = new StageRadiologie();
        stageRadiologie1.setId(1L);
        StageRadiologie stageRadiologie2 = new StageRadiologie();
        stageRadiologie2.setId(stageRadiologie1.getId());
        assertThat(stageRadiologie1).isEqualTo(stageRadiologie2);
        stageRadiologie2.setId(2L);
        assertThat(stageRadiologie1).isNotEqualTo(stageRadiologie2);
        stageRadiologie1.setId(null);
        assertThat(stageRadiologie1).isNotEqualTo(stageRadiologie2);
    }
}
