package co.com.worldoffice.shopping.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.worldoffice.shopping.dto.ProductItemDTO;
import co.com.worldoffice.shopping.dto.ShoppingCartDTO;
import co.com.worldoffice.shopping.entity.Product;
import co.com.worldoffice.shopping.entity.ProductItem;
import co.com.worldoffice.shopping.entity.ShoppingCart;
import co.com.worldoffice.shopping.repository.ProductRepository;
import co.com.worldoffice.shopping.repository.ShoppingCartRepository;
import co.com.worldoffice.shopping.service.IShoppingCartService;

@Service
public class ShoppingCartService implements IShoppingCartService {
	
	@Autowired
	private ShoppingCartRepository shoppingCarRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public ShoppingCart create(ShoppingCartDTO inputData) {
		// TODO Auto-generated method stub
		ShoppingCart cart = new ShoppingCart();
		cart.setOwner(inputData.getOwner());
		shoppingCarRepo.save(cart);
		return cart;
	}

	@Override
	@Transactional
	public ShoppingCart addProduct(ProductItemDTO productInfo) throws Exception{
		// TODO Auto-generated method stub
		Product product = queryProductById(productInfo.getIdProduct());
		
		if( productInfo.getAmount() > product.getStock() ) {
			throw new Exception("La cantidad de productos es superior a la existencia en Stock");
		}
		
		Optional<ShoppingCart> shoppingCarQry = shoppingCarRepo.findById(productInfo.getIdShoppingCar());
		ShoppingCart shopCar = null;
		
		if(shoppingCarQry.isPresent()) {
			shopCar=shoppingCarQry.get();
		} else {
			throw new Exception("Carrito de compra con id: " + productInfo.getIdShoppingCar() + " no existe en base de datos");
		}
		
		ProductItem item = createProductItem(product,shopCar,productInfo);
		shopCar.addItem(item);
		
		shoppingCarRepo.save(shopCar);
		return shopCar;
	}
	
	
	private ProductItem createProductItem(Product product, ShoppingCart shopCar, ProductItemDTO productInfo ) {
		// TODO Auto-generated method stub
		ProductItem item = new ProductItem();
		item.setAmount(productInfo.getAmount());
		item.setPrice(product.getPrice());
		item.setProduct(product);
		return item;
	}

	private Product queryProductById(long idProduct) throws Exception{
		Optional<Product> productQry = productRepo.findById(idProduct);
		Product product= null;
		if (productQry.isPresent()) {
			product = productQry.get();
		} else {
			throw new Exception("Producto con id: " + idProduct + " no existe en base de datos");
		}
		return product;
	}
	

}
