package co.com.worldoffice.shopping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "Product", path = "/Product")
public class ProductController {
	
	@GetMapping( name = "status", path= "/")
	public String status() {
		return "Product API is Alive!!!";
	}
}
