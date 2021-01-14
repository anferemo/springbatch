package co.com.worldoffice.shopping.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.worldoffice.shopping.entity.Product;
import co.com.worldoffice.shopping.repository.ProductRepository;
import co.com.worldoffice.shopping.service.IProductService;

@Service
public class ProductService implements IProductService{
	
	@Autowired
	private ProductRepository productRepo;

	@Override
	public List<Product> findAll(Pageable paging) {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<>();
		Page<Product> productPage;
		productPage=productRepo.findAll(paging);
		products = productPage.getContent();
		return products;		
	}

	@Override
	public List<Product> findByNameLike(String name, Pageable paging) {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<>();
		Page<Product> productPage;
		productPage=productRepo.findAllByNameLike(name, paging);
		products = productPage.getContent();
		return products;
	}

	@Override
	public List<Product> findByBrand(String brand, Pageable paging) {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<>();
		Page<Product> productPage;
		productPage=productRepo.findByBrand(brand, paging);
		products = productPage.getContent();
		return products;
	}
	
	
	
}
