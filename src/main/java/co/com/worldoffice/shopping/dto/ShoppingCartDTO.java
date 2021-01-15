package co.com.worldoffice.shopping.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ShoppingCartDTO {
	
	@NotNull(message="Owner is Required")
	@Size(min=1, message="Owner cannot be Blank")
	private String owner;

	public ShoppingCartDTO() {
		super();
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
}
