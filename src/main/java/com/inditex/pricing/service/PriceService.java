package com.inditex.pricing.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inditex.pricing.dto.PriceDTO;
import com.inditex.pricing.dto.PriceRequest;
import com.inditex.pricing.exception.PriceNotFoundException;
import com.inditex.pricing.model.Price;
import com.inditex.pricing.repository.PriceRepository;
import com.inditex.pricing.service.interfaces.IPriceService;


@Service
public class PriceService implements IPriceService {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	private final ModelMapper mapper = new ModelMapper();
	
	@Autowired
	private PriceRepository priceRepository;


	@Transactional
	public PriceDTO findValid(PriceRequest priceRequest) {
		Price price = priceRepository
				.getValidPrice(priceRequest.getApplicationDate(),
						priceRequest.getProductId(),
						priceRequest.getBrandId())
				.orElseThrow(PriceNotFoundException::new);
		
		return mapper.map(price, PriceDTO.class);
	}

	public PriceDTO findById(Long id) {
		Price price = priceRepository.findById(id)
				.orElseThrow(PriceNotFoundException::new);
		
		return mapper.map(price, PriceDTO.class);
	}
	
}
