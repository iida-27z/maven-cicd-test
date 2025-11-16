package io.github.test.app;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppApplicationIT {

	@Test
	@Tag("integration")
	void contextLoads() {
	}
}