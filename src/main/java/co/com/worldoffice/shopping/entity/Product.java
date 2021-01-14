package co.com.worldoffice.shopping.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Entity
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1132750304411998783L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String brand;
	private BigDecimal price;
	private int stock;
	private String state;
	private BigDecimal discount_percent;
	
	public Product() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
