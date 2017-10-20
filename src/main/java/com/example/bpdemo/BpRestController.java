package com.example.bpdemo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@ConfigurationProperties(prefix = "bpapp")
public class BpRestController {
	
	private String firstName;
	private String lastName;

	private String template = "%s %s says: Hello, %s!";
	private static int counter = 0;

	@RequestMapping(method=RequestMethod.GET, value="/sayHello/{userName}")
	public Greeting sayHello(@PathVariable(name="userName") String userName) {
		return new Greeting(counter++, String.format(template, firstName, lastName, userName));
	}

	@RequestMapping(method=RequestMethod.POST, value="/square/{num}")
	public String square(@PathVariable(name="num") int num) {
		int sq = num * num;
		return "square of " + num + " = " + sq;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the counter
	 */
	public static int getCounter() {
		return counter;
	}

	/**
	 * @param counter the counter to set
	 */
	public static void setCounter(int counter) {
		BpRestController.counter = counter;
	}
	

}

class Greeting {

	private final long id;
	private final String content;

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}
