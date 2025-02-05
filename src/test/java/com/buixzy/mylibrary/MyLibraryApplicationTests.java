package com.buixzy.mylibrary;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.buixzy.mylibrary.enums.StateCopies;

@SpringBootTest
class MyLibraryApplicationTests {

	@Test
	void contextLoads() {
		Assert.isTrue(StateCopies.fromString("new").equals(StateCopies.NEW), "StateCopies NEW is not correct.");
	}

}
