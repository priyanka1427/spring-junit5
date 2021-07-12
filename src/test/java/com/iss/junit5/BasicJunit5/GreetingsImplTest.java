package com.iss.junit5.BasicJunit5;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GreetingsImplTest {
	
	@Mock
	private GreetingServiceImpl service;
	
	@InjectMocks
	private GreetingsImpl greeting;

	@Test
	public void greetShouldReturnAValidOutput() {
		when(service.greet("JUnit")).thenReturn("Hello JUnit");
		String result = greeting.greet("JUnit");
		Assertions.assertNotNull(result);
		Assertions.assertEquals("Hello JUnit", result);
	}
	
	@Test
	public void greetShouldReturnAnExceptionWhen_NameIsNull() {
		System.out.println("greetShouldReturnAnExceptionWhen_NameIsNull");
		doThrow(IllegalArgumentException.class).when(service).greet(null);
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			greeting.greet(null);
		});
	}

	@Test
	public void greetShouldReturnAnExceptionWhen_NameIsBlank() {
		System.out.println("greetShouldReturnAnExceptionWhen_NameIsBlank");
		doThrow(IllegalArgumentException.class).when(service).greet("");
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			greeting.greet("");
		});
	}
	
}
