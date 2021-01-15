package co.com.worldoffice.shopping.service;


import co.com.worldoffice.shopping.dto.ProductItemDTO;
import co.com.worldoffice.shopping.dto.ShoppingCartDTO;
import co.com.worldoffice.shopping.entity.ShoppingCart;

public interface IShoppingCartService {
	ShoppingCart create(ShoppingCartDTO inputData);
	ShoppingCart addProduct(ProductItemDTO productInfo) throws Exception;
}
