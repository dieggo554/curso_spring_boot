package com.example.rest.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Es un spring-boot controller, no un @RestController
// No lo ponemos en el paquete controllers para que no lo mapee Swagger
@Controller
public class HomeController {
	@RequestMapping("/")
	public String home() {
		// Redirige "/" a la página "swagger-ui.html"
		return "redirect:/swagger-ui.html";
	}
}
