package com.inditex.pricing.service.interfaces;

import com.inditex.pricing.dto.PriceDTO;
import com.inditex.pricing.dto.PriceRequest;

public interface IPriceService {

	PriceDTO findValid(PriceRequest priceRequest);
	
	PriceDTO findById(Long id);
	
}
