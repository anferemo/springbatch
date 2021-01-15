package co.com.worldoffice.shopping.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.worldoffice.shopping.dto.ProductItemDTO;
import co.com.worldoffice.shopping.dto.ShoppingCartDTO;
import co.com.worldoffice.shopping.entity.ShoppingCart;
import co.com.worldoffice.shopping.service.IProductService;
import co.com.worldoffice.shopping.service.IShoppingCartService;

@RestController
@RequestMapping(name = "Shopping-Cart", path = "/ShopCart")
public class ShoppingCartController {
	
	@Autowired
	private IShoppingCartService shopingCarService;
	
	
	@GetMapping( name = "status", path= "/")
	public String status() {
		return "Shopping Cart API is Alive!!!";
	}
	
	@PostMapping(name="Create Shopping Cart", path="/")
	public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody ShoppingCartDTO input){
		Map<String, Object> response = new HashMap<>();
		try {
			ShoppingCart createdCar = shopingCarService.create(input);
			response.put("Result", createdCar);
			response.put("Message", "Shopping Car Created");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch (Exception ex) {
			ex.printStackTrace();
			response.put("Description", "There was an Error");
			response.put("Exception", ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(name="Add Product to Shopping Cart", path="/item")
	public ResponseEntity<Map<String, Object>> addProductToCar(@Valid @RequestBody ProductItemDTO input){
		Map<String, Object> response = new HashMap<>();
		try {
			ShoppingCart car= shopingCarService.addProduct(input);
			response.put("Result", car);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch (Exception ex) {
			ex.printStackTrace();
			response.put("Description", "There was an Error");
			response.put("Exception", ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
