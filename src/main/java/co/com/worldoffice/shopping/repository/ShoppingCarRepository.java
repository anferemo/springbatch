package co.com.worldoffice.shopping.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.worldoffice.shopping.entity.ShoppingCar;

public interface ShoppingCarRepository extends PagingAndSortingRepository<ShoppingCar, Long>{

}
