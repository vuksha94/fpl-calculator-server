package com.springboot.fplcalculatorserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleDemoController {

	@GetMapping("/")
	public String home() {
		return "Home";
	}

	@GetMapping("/user")
	public String user() {
		return "User";
	}

	@GetMapping("/admin")
	public String admin() {
		return "Admin";
	}
}
