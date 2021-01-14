package co.com.worldoffice.shopping.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.worldoffice.shopping.entity.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>{
	@Query("Select p from Product p where p.name like %:name% ")
	Page<Product> findAllByNameLike(@Param("name")String name, Pageable pageable);
	
	Page<Product> findByBrand(@Param("brand")String brand, Pageable pageable);
	
	Page<Product>  findByPriceLessThanEqual(@Param("maxValue")BigDecimal price, Pageable pageable);
	
	Page<Product>  findByPriceGreaterThanEqual(@Param("minValue")BigDecimal price, Pageable pageable);
	
	@Query("Select p from Product p where p.price <= :maxValue and p.price >= :minValue")
	Page<Product> findProductByPriceRange(@Param("minValue")BigDecimal minValue, @Param("maxValue")BigDecimal maxValue, Pageable pageable);
	
}
