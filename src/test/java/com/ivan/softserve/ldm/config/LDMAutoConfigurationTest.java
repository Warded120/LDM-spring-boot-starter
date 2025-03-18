package com.ivan.softserve.ldm.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LDMAutoConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(LDMAutoConfiguration.class));

    @Test
    void shouldContain() {
      contextRunner.run(context -> {
          assertTrue(context.containsBean("LDMdotenv"));
      });
    }
}