package io.vultr.cld.paycons.config.db;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("io.vultr.cld.paycons.persistence.repo")
public class DbConfig {
}
