package com.inditex.pricing.service.interfaces;

import com.inditex.pricing.dto.PriceDTO;
import com.inditex.pricing.dto.PriceRequest;
import com.inditex.pricing.dto.PriceWithQuantityRequest;

public interface IPriceService {

	PriceDTO findValid(PriceRequest priceRequest);
	
	PriceDTO findValidWithQuantity(PriceWithQuantityRequest priceRequest);
	
	PriceDTO findById(Long id);
	
}
