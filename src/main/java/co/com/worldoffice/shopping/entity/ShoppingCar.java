package co.com.worldoffice.shopping.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "SHOPPING_CART")
public class ShoppingCar implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2809020289199295917L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String owner;
	
	@OneToMany(cascade=CascadeType.ALL, targetEntity = ProductItem.class )
	@JoinColumn(name="cart_id")
	private List<ProductItem> items;
	
	@Column(name="finished")
	private boolean isPurchased;

	public ShoppingCar() {
		super();
		this.items = new ArrayList<>();
		this.isPurchased = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public List<ProductItem> getItems() {
		return items;
	}

	public void setItems(List<ProductItem> items) {
		this.items = items;
	}

	public boolean isPurchased() {
		return isPurchased;
	}

	public void setPurchased(boolean isPurchased) {
		this.isPurchased = isPurchased;
	}
	
	public void addItem(ProductItem item) {
		this.items.add(item);
	}
	
	
}
