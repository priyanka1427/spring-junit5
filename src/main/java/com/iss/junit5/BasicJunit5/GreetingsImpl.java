package com.iss.junit5.BasicJunit5;

public class GreetingsImpl implements Greetings{
	
	public GreetingServiceImpl service;
	
	public String greet(String name) {
		return service.greet(name);
	}

}
