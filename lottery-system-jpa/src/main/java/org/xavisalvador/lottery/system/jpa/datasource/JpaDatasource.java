package org.xavisalvador.lottery.system.jpa.datasource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.xavisalvador.lottery.system.jpa.repository")
@EntityScan("org.xavisalvador.lottery.system.jpa.entity")
class JpaDatasource {

}
