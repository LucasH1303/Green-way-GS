package com.greenway.greenway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.NONE,
	classes = GreenWayApplication.class,
	properties = {
		"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,org.springframework.boot.autoconfigure.amqp.RabbitRepositoriesAutoConfiguration,org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration",
		"spring.cache.type=none",
		"management.endpoints.enabled-by-default=false",
		"spring.jpa.hibernate.ddl-auto=create-drop",
		"spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE"
	}
)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class GreenWayApplicationTests {

	@Test
	void contextLoads() {
		// Teste básico para verificar se o contexto do Spring Boot carrega corretamente
		// Este teste verifica se todas as configurações do Spring estão corretas
		// Se este teste passar, significa que a aplicação pode ser inicializada corretamente
	}

}

