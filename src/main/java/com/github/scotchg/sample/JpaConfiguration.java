package com.github.scotchg.sample;

import com.github.scotchg.sample.Repository.BaseRepositoryImpl;
import com.github.scotchg.sample.jpa.RequestHeaderAuditAware;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
@Configuration
public class JpaConfiguration {

    @ConfigurationProperties(prefix = "system.jpa")
    @Component
    @Validated
    @Data
    private static class SystemJpaConfiguration {
        @NotNull
        private String auditor;
    }

    @Bean
    public AuditorAware<String> auditorProvider(SystemJpaConfiguration systemJpaConfiguration) {
        return new RequestHeaderAuditAware("X-Request-User",systemJpaConfiguration.getAuditor());
    }
}
