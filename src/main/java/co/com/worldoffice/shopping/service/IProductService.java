package co.com.worldoffice.shopping.service;

import java.math.BigDecimal;
import java.util.List;

//import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.com.worldoffice.shopping.entity.Product;

public interface IProductService {
	List<Product> findAll(Pageable paging);
	
	List<Product> findByNameLike(String name,Pageable paging);
	
	List<Product> findByBrand(String brand, Pageable paging);
	
	List<Product> findByPriceRange(BigDecimal minValue, BigDecimal maxValue, Pageable paging);
	
}
