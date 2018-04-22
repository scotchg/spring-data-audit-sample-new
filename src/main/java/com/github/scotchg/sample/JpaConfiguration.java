package com.github.scotchg.sample;

import com.github.scotchg.sample.Repository.BaseRepositoryImpl;
import com.github.scotchg.sample.jpa.audit.OffsetDatetimeProvider;
import com.github.scotchg.sample.jpa.audit.RequestHeaderAuditAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@EnableJpaAuditing(auditorAwareRef="auditorProvider",dateTimeProviderRef = "dateTimeProvider")
@Configuration
public class JpaConfiguration {

    @Bean
    public DateTimeProvider dateTimeProvider() {
        return new OffsetDatetimeProvider();
    }

    @Bean
    public AuditorAware<String> auditorProvider(
            @Value("${system.jpa.auditUserHeader}") String auditorHeaderKey,
            @Value("${spring.application.name}") String applicationName) {
        return new RequestHeaderAuditAware(auditorHeaderKey, applicationName);
    }
}
