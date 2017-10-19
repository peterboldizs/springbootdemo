package com.example.bpdemo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@ConfigurationProperties(prefix = "bpapp")
public class BpRestController {

	private static final String template = "Hello, %s!";
	private static int counter = 0;

	@RequestMapping("/sayhello")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter++, String.format(template, name));
	}

	@RequestMapping(method=RequestMethod.POST, value="/square")
	public String square(@RequestParam(value = "num") int num) {
		//int num = Integer.parseInt(number);
		int sq = num * num;
		return "square of " + num + " = " + sq;
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
