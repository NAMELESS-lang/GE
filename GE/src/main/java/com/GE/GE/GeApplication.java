package com.GE.GE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Controller
public class GeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeApplication.class, args);
	}
	
	@GetMapping("/cadastro")
	public String ola() {
		return "cadastro_item";
	}

}
