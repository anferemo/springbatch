package co.com.worldoffice.shopping.batch;

import java.math.BigDecimal;
import javax.validation.constraints.Min;

public class Product {
	private String name;
	private String brand;
	private BigDecimal price;
	
	@Min(value=1)
	private int stock;
	private String state;
	private BigDecimal discount_percent;
	
	public Product() {
		super();
	}
	
	public Product(String name, String brand, BigDecimal price, int stock, String state, BigDecimal discount_percent) {
		super();
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.stock = stock;
		this.state = state;
		this.discount_percent = discount_percent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public BigDecimal getDiscount_percent() {
		return discount_percent;
	}
	public void setDiscount_percent(BigDecimal discount_percent) {
		this.discount_percent = discount_percent;
	}
	
	
}
