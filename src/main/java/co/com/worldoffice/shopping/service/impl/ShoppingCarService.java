package co.com.worldoffice.shopping.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.worldoffice.shopping.dto.ProductItemDTO;
import co.com.worldoffice.shopping.dto.ShoppingCarDTO;
import co.com.worldoffice.shopping.entity.Product;
import co.com.worldoffice.shopping.entity.ProductItem;
import co.com.worldoffice.shopping.entity.ShoppingCar;
import co.com.worldoffice.shopping.repository.ProductRepository;
import co.com.worldoffice.shopping.repository.ShoppingCarRepository;
import co.com.worldoffice.shopping.service.IShoppingCartService;

@Service
public class ShoppingCarService implements IShoppingCartService {
	
	@Autowired
	private ShoppingCarRepository shoppingCarRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Override
	@Transactional
	public ShoppingCar create(ShoppingCarDTO inputData) {
		// TODO Auto-generated method stub
		ShoppingCar cart = new ShoppingCar();
		cart.setOwner(inputData.getOwner());
		shoppingCarRepo.save(cart);
		return cart;
	}

	@Override
	@Transactional
	public ShoppingCar addProduct(ProductItemDTO productInfo) throws Exception{
		// TODO Auto-generated method stub
		Product product = queryProductById(productInfo.getIdProduct());
		
		if( productInfo.getAmount() > product.getStock() ) {
			throw new Exception("La cantidad de productos es superior a la existencia en Stock");
		}
		
		ShoppingCar shopCar = findById(productInfo.getIdShoppingCar());
		
		ProductItem item = createProductItem(product,shopCar,productInfo);
		if (item!=null) {
			shopCar.addItem(item);
		}
		
		shoppingCarRepo.save(shopCar);
		return shopCar;
	}
	
	
	private ProductItem createProductItem(Product product, ShoppingCar shopCar, ProductItemDTO productInfo ) throws Exception {
		// TODO Auto-generated method stub
		//Validar que el producto no exista en el carrito de compra
		boolean itemExist = shopCar.getItems().stream().filter( (item) -> item.getProduct().getId()==productInfo.getIdProduct()).findFirst().isPresent();
		if (itemExist) {
			throw new Exception ("No se puede adicionar La linea de Producto " + productInfo.getIdProduct() + " porque ya existe una creada");
		}
		
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
	
	@Override
	public ShoppingCar findById(long id) throws Exception{
		Optional<ShoppingCar> shoppingCarQry = shoppingCarRepo.findById(id);
		ShoppingCar shopCar = null;
		
		if(shoppingCarQry.isPresent()) {
			shopCar=shoppingCarQry.get();
		} else {
			throw new Exception("Carrito de compra con id: " + id + " no existe en base de datos");
		}
		return shopCar;
	}

	@Override
	public List<Product> findAssociatedProducts(long idShoppingCar) throws Exception {
		// TODO Auto-generated method stub
		ShoppingCar shopCar = findById(idShoppingCar);
		List<Product> productList = new ArrayList<>();
		shopCar.getItems().stream().forEach(
					(item)-> { productList.add(item.getProduct()); }
				);		
		return productList;
	}

	@Override
	@Transactional
	public ShoppingCar deleteProducts(long idShoppingCart) throws Exception {
		// TODO Auto-generated method stub
		ShoppingCar shopCar = findById(idShoppingCart);
		shopCar.getItems().clear();
		//shopCar.setItems(new ArrayList<>());
		shoppingCarRepo.save(shopCar);
		return shopCar;
	}

	@Override
	@Transactional
	public void doPurchase(long idShoppingCart) throws Exception {
		// TODO Auto-generated method stub
		ShoppingCar shopCar = findById(idShoppingCart);
		if (shopCar.isPurchased()) {
			throw new Exception("Ya se realizó el proceso de compra correspondiente al carrito con id " + idShoppingCart ); 
		}
		for (ProductItem item: shopCar.getItems()) {
			Product product = item.getProduct();
			int totalStock = product.getStock() - item.getAmount();
			if (totalStock >= 0) {
				product.setStock(totalStock);
				productRepo.save(product);
			} else {
				throw new Exception("El producto con id " + item.getProduct().getId()  + " No cuenta con existencias para la compra");
			}
		}
		shopCar.setPurchased(true);
		shoppingCarRepo.save(shopCar);
	}
	

}
