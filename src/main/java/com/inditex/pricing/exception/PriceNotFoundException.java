package com.inditex.pricing.exception;

import com.inditex.pricing.enums.MessageErrorEnum;

public class PriceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3261916389479452914L;

	public PriceNotFoundException() {
		super(MessageErrorEnum.PRICE_NOT_FOUND_EXCEPTION.getDescription());
	}
	
}
