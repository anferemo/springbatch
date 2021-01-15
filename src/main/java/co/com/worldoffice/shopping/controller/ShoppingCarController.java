package co.com.worldoffice.shopping.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.worldoffice.shopping.dto.ProductItemDTO;
import co.com.worldoffice.shopping.dto.ShoppingCarDTO;
import co.com.worldoffice.shopping.entity.Product;
import co.com.worldoffice.shopping.entity.ShoppingCar;
import co.com.worldoffice.shopping.service.IShoppingCartService;

@RestController
@RequestMapping(name = "Shopping-Cart", path = "/ShopCart")
public class ShoppingCarController {
	
	private static Logger log = LogManager.getLogger(ShoppingCarController.class);
	
	@Autowired
	private IShoppingCartService shopingCarService;
	
	
	@GetMapping( name = "status", path= "/")
	public String status() {
		return "Shopping Cart API is Alive!!!";
	}
	
	@PostMapping(name="Create Shopping Cart", path="/")
	public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody ShoppingCarDTO input){
		Map<String, Object> response = new HashMap<>();
		try {
			ShoppingCar createdCar = shopingCarService.create(input);
			response.put("Result", createdCar);
			response.put("Message", "Shopping Car Created");
			log.info(response);
			log.info("create() succesful");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception ex) {
			log.error(ex);
			response.put("Description", "There was an Error");
			response.put("Exception", ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(name="Add Product to Shopping Cart", path="/item")
	public ResponseEntity<Map<String, Object>> addProductToCar(@Valid @RequestBody ProductItemDTO input){
		Map<String, Object> response = new HashMap<>();
		try {
			ShoppingCar car= shopingCarService.addProduct(input);
			response.put("Result", car);
			log.info(response);
			log.info("addProductToCar() succesful");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception ex) {
			log.error(ex);
			response.put("Description", "There was an Error");
			response.put("Exception", ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@GetMapping( name = "Query Shopping Cart Products", path= "/Products")
	public ResponseEntity<Map<String, Object>> findCarProducts(@RequestParam(defaultValue = "0") long idShoppingCar) {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Product> products = shopingCarService.findAssociatedProducts(idShoppingCar);
			response.put("Result", products);
			log.info(response);
			log.info("findCarProducts() succesful");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception ex) {
			log.error(ex);
			response.put("Description", "There was an Error");
			response.put("Exception", ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(name="Clear ShoppingCart products", path="/Products")
	public ResponseEntity<Map<String, Object>> deleteProducts(@RequestParam(required = true) long idShoppingCar){
		Map<String, Object> response = new HashMap<>();
		try {
			ShoppingCar shopCar = shopingCarService.deleteProducts(idShoppingCar);
			response.put("Result", shopCar);
			log.info(response);
			log.info("deleteProducts() succesful");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception ex) {
			log.error(ex);
			response.put("Description", "There was an Error");
			response.put("Exception", ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(name="Process to do purchase process", path="/Purchase")
	public ResponseEntity<Map<String, Object>> doPurchase(@RequestParam("idShoppingCart") long idShoppingCart){
		Map<String, Object> response = new HashMap<>();
		try {
			shopingCarService.doPurchase(idShoppingCart);
			response.put("Result", "Items Succesfully sold");
			log.info(response);
			log.info("doPurchase() succesful");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception ex) {
			log.error(ex);
			response.put("Description", "There was an Error");
			response.put("Exception", ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
