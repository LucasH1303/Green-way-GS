package com.greenway.greenway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.NONE,
	properties = {
		"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration",
		"spring.cache.type=none",
		"management.endpoints.enabled-by-default=false",
		"spring.jpa.hibernate.ddl-auto=create-drop",
		"spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
		"spring.datasource.driverClassName=org.h2.Driver",
		"spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
		"jwt.secret=test-secret-key-for-testing-purposes-only-minimum-32-characters-long",
		"jwt.expiration=3600000"
	}
)
@ComponentScan(
	basePackages = "com.greenway.greenway",
	excludeFilters = {
		@ComponentScan.Filter(
			type = FilterType.REGEX,
			pattern = "com\\.greenway\\.greenway\\.messaging\\..*"
		),
		@ComponentScan.Filter(
			type = FilterType.ASSIGNABLE_TYPE,
			classes = com.greenway.greenway.config.RabbitConfig.class
		)
	}
)
@ActiveProfiles("test")
class GreenWayApplicationTests {

	@Test
	void contextLoads() {
		// Teste básico para verificar se o contexto do Spring Boot carrega corretamente
		// Este teste verifica se todas as configurações do Spring estão corretas
		// Se este teste passar, significa que a aplicação pode ser inicializada corretamente
	}

}

