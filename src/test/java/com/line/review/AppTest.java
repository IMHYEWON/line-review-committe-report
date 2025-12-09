package com.line.review;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AppTest {
    @Test
    void shouldCreateApp() {
        App app = new App();
        assertThat(app).isNotNull();
    }
}

