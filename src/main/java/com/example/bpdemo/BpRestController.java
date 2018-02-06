package com.example.bpdemo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.*;

@RestController
@RequestMapping("/api")
@ConfigurationProperties(prefix = "bpapp")
public class BpRestController {
	
	private String firstName;
	private String lastName;

	private String helloTemplate = "%s %s says: Hello, %s!";
	private String goodbyeTemplate = "%s says: Good bye, %s!";
	private static int counter = 0;
	
	private final Logger logger = LoggerFactory.getLogger(BpRestController.class);

	/**
	 * uses GET and PathVariable
	 * @param userName
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/sayHello/{userName}")
	public Greeting sayHello(@PathVariable(name="userName") String userName) {
		logger.info("sayHello invoked with userName: " + userName);
		return new Greeting(counter++, String.format(helloTemplate, firstName, lastName, userName));
	}
	
	/**
	 * uses GET and RequestParam
	 * @param userName
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/sayGoodbye")
	public Greeting sayGoodbye(@RequestParam(name="userName") String userName) {
	    //it is just a trigger comment
		logger.info("sayGoodbye invoked with userName:" + userName);
		return new Greeting(counter++, String.format(goodbyeTemplate, firstName, userName));
	}

	/**
	 * Accepts both GET and POST and uses PathVariable
	 * @param num
	 * @return
	 */
	@RequestMapping(value="/square/{num}")
	public String square(@PathVariable(name="num") int num) {
		logger.info("square invoked with num: " + num);
		int sq = num * num;
		return "square of " + num + " = " + sq;
	}
	
	/**
	 * Accepts only POST and uses PathVariable
	 * @param num
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/cube/{num}")
	public String cube(@PathVariable(name="num") int num) {
		logger.info("cube invoked with num: " + num);
		int cube = num * num * num;
		return "square of " + num + " = " + cube;
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
