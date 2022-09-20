package io.vultr.cld.paycons;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("h2")
@SpringBootTest
class PayConsApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @DisplayName("Check NonNull ApplicationContext - Positive")
    void contextLoads() {
        assertNotNull(applicationContext);
    }

}
