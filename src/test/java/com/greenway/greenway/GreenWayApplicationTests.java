package com.greenway.greenway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class GreenWayApplicationTests {

	@Test
	void contextLoads() {
		// Teste básico para verificar se o contexto do Spring Boot carrega corretamente
		// Este teste verifica se todas as configurações do Spring estão corretas
	}

}

