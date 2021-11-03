package com.stc.appointmentmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {
    @Bean(name = "auditingDateTimeProvider")
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(LocalDateTime.now());
    }

    @Bean
    public AuditorAware<String> auditorProvider() {

        /*
          if you are using spring security, you can get the currently logged username with following code segment.
          SecurityContextHolder.getContext().getAuthentication().getName()
         */
        return () -> Optional.ofNullable("chathuranga");
    }
}
