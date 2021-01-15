package co.com.worldoffice.shopping.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.worldoffice.shopping.entity.ProductItem;

public interface ProductItemRepository extends PagingAndSortingRepository<ProductItem, Long> {

}
