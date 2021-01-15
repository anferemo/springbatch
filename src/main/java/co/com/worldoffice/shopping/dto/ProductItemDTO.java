package co.com.worldoffice.shopping.dto;

import javax.validation.constraints.Min;

public class ProductItemDTO {
	
	private Long idShoppingCar;
	
	private Long idProduct;
	
	@Min(value=1)
	private int amount;

	public ProductItemDTO() {
		
	}

	public Long getIdShoppingCar() {
		return idShoppingCar;
	}

	public void setIdShoppingCar(Long idShoppingCar) {
		this.idShoppingCar = idShoppingCar;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
	
}
