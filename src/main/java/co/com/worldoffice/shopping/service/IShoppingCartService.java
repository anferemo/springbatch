package co.com.worldoffice.shopping.service;


import co.com.worldoffice.shopping.dto.ProductItemDTO;
import co.com.worldoffice.shopping.dto.ShoppingCarDTO;
import co.com.worldoffice.shopping.entity.ShoppingCar;

public interface IShoppingCartService {
	ShoppingCar create(ShoppingCarDTO inputData);
	ShoppingCar addProduct(ProductItemDTO productInfo) throws Exception;
}
