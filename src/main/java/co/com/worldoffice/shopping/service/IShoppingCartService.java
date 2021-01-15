package co.com.worldoffice.shopping.service;


import java.util.List;

import co.com.worldoffice.shopping.dto.ProductItemDTO;
import co.com.worldoffice.shopping.dto.ShoppingCarDTO;
import co.com.worldoffice.shopping.entity.ShoppingCar;
import co.com.worldoffice.shopping.entity.Product;

public interface IShoppingCartService {
	ShoppingCar create(ShoppingCarDTO inputData);
	ShoppingCar addProduct(ProductItemDTO productInfo) throws Exception;
	List<Product> findAssociatedProducts(long idShoppingCar) throws Exception;
	ShoppingCar findById(long id) throws Exception;
	ShoppingCar deleteProducts(long idShoppingCart) throws Exception;
	void doPurchase(long idShoppingCart) throws Exception;
}
