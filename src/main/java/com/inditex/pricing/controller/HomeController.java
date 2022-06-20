package com.inditex.pricing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HomeController {
	

	@GetMapping("/")
	public Object home() {
		return "Hello from Inditex Pricing Home";
	}

	@GetMapping("/ping")
	public Object ping() {
		return "Hello from Inditex Pricing ping";
	}

}
