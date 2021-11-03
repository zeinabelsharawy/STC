package com.stc.appointmentmanagement.config;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class FlywayConfiguration {

    @Bean
    @Profile({"tst"})
    public FlywayMigrationStrategy migrationStrategy() {
        return flyway -> {
            flyway.clean();
            flyway.migrate();
        };
    }
}
