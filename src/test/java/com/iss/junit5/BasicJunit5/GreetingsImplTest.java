package com.iss.junit5.BasicJunit5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GreetingsImplTest {
	
	private Greetings greeting;

	@BeforeEach
	public void setup() {
		System.out.println("setup");
		greeting = new GreetingsImpl();
	}

	@Test
	public void greetShouldReturnAValidOutput() {
		System.out.println("greetShouldReturnAValidOutput");
		String result = greeting.greet("JUnit");
		Assertions.assertNotNull(result);
		Assertions.assertEquals("HelloJUnit", result);
	}
	
	@Test
	public void greetShouldReturnAnExceptionWhen_NameIsNull() {
		System.out.println("greetShouldReturnAnExceptionWhen_NameIsNull");
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			greeting.greet(null);
		});
	}

	@Test
	public void greetShouldReturnAnExceptionWhen_NameIsBlank() {
		System.out.println("greetShouldReturnAnExceptionWhen_NameIsBlank");
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			greeting.greet("");
		});
	}
	
	@AfterEach
	public void teardown() {
		System.out.println("teardown");
		greeting = null;
	}
}
