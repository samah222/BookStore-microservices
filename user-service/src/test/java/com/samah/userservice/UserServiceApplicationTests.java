package com.samah.userservice;

import com.samah.userservice.controller.InfoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private InfoController infoController;
	@Test
	void contextLoads() {
		assertThat(infoController).isNotNull();
	}

}
