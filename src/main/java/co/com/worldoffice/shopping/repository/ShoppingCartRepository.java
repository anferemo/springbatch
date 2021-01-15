package co.com.worldoffice.shopping.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.worldoffice.shopping.entity.ShoppingCart;

public interface ShoppingCartRepository extends PagingAndSortingRepository<ShoppingCart, Long>{

}
