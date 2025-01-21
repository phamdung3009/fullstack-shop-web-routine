package com.fullstack.shop.web.routine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	@GetMapping(value = {"/", "/public"})
	public String home() {
		return "home";
	}

	@GetMapping("/private")
	public String privatePage() {
		return "private";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

}
