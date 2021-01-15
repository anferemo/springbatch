package co.com.worldoffice.shopping.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.worldoffice.shopping.entity.Product;
import co.com.worldoffice.shopping.service.IProductService;

@RestController
@RequestMapping(name = "Product", path = "/Product")
public class ProductController {
	
	private static Logger log = LogManager.getLogger(ProductController.class);
	
	@Autowired
	private IProductService productSvc;
	
	@GetMapping( name = "status", path= "/")
	public String status() {
		return "Product API is Alive!!!";
	}
	
	@GetMapping( name = "status", path= "/name")
	public ResponseEntity<Map<String, Object>> findProductByName(
			@RequestParam(required = false) String name,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size){
		Map<String, Object> response = new HashMap<>();
		try {
			List<Product> result = new ArrayList<>();
			Pageable paging = PageRequest.of(page, size);
			if (name==null || name.isEmpty()) {
				result=productSvc.findAll(paging);
			} else {
				result=productSvc.findByNameLike(name, paging);
			}

		    response.put("tutorials", result);
		    response.put("totalItems", result.size());
			log.info("findProductByName() succesful");
		    return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception ex) {
			log.error(ex);
			response.put("Description", "There was an Error");
			response.put("Exception", ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping( name = "status", path= "/brand")
	public ResponseEntity<Map<String, Object>> findProductByBrand(
			@RequestParam(required = false) String brand,
			@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size) {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Product> result = new ArrayList<>();
			Pageable paging = PageRequest.of(page, size);
			if (brand==null || brand.isEmpty()) {
				result=productSvc.findAll(paging);
			} else {
				result=productSvc.findByBrand(brand, paging);
			}

		    response.put("tutorials", result);
		    response.put("totalItems", result.size());
		    log.info("findProductByBrand() succesful");
		    return new ResponseEntity<>(response, HttpStatus.OK);
		} catch(Exception ex) {
			log.error(ex);
			response.put("Description", "There was an Error");
			response.put("Exception", ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping( name = "status", path= "/price")
	public ResponseEntity<Map<String, Object>> findProductByPriceRange(
			@RequestParam(required = false) BigDecimal minValue,
			@RequestParam(required = false) BigDecimal maxValue,
			@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size) {
		log.info("findProductByPriceRange() start");
		Map<String, Object> response = new HashMap<>();
		try {
			List<Product> result = new ArrayList<>();
			Pageable paging = PageRequest.of(page, size);
			result=productSvc.findByPriceRange(minValue, maxValue, paging);
			
			response.put("tutorials", result);
		    response.put("totalItems", result.size());
		    log.info("findProductByPriceRange() succesful");
		    return new ResponseEntity<>(response, HttpStatus.OK);
		} catch(Exception ex) {
			log.error(ex);
			response.put("Description", "There was an Error");
			response.put("Exception", ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
