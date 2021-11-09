package org.xavisalvador.lottery.system.boot;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties", properties = {"server.port=8080"})
public class MainApplicationTest {

	/**
	 * Test that application context loads.
	 */
	@Test
	public void contextLoads() {
		Assertions.assertThat(SpringBootVersion.getVersion()).isNotNull();
	}

}


