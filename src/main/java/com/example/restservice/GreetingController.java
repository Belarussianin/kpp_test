package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Your number is: %s, number that is %s is %d";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/task")
	public Greeting greeting(@RequestParam(value = "number", defaultValue = "100") String number,
							 @RequestParam(value = "side", defaultValue = ">") char side) {
		int num = Integer.parseInt(number);
		int number_to_output=10;
		if (side =='>') {
			int max=1000;
			max-=num;
			number_to_output =(int) (Math.random() * (max +1)) + num;

		}else if(side == '<') {

			number_to_output= (int) (Math.random()* (num + 1));
		}
		return new Greeting(counter.incrementAndGet(), String.format(template, number,side,number_to_output));
	}
}


