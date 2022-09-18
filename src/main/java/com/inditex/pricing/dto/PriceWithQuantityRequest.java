package com.inditex.pricing.dto;

import javax.validation.constraints.NotNull;

public class PriceWithQuantityRequest extends PriceRequest {

	@NotNull
	private Integer quantity;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
