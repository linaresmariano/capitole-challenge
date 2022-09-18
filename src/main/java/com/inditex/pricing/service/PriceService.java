package com.inditex.pricing.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.inditex.pricing.dto.PriceDTO;
import com.inditex.pricing.dto.PriceRequest;
import com.inditex.pricing.dto.PriceWithQuantityRequest;
import com.inditex.pricing.exception.PriceNotFoundException;
import com.inditex.pricing.model.Price;
import com.inditex.pricing.repository.PriceRepository;
import com.inditex.pricing.service.interfaces.IPriceService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PriceService implements IPriceService {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	private final ModelMapper mapper = new ModelMapper();
	
	private final PriceRepository priceRepository;


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

	@Override
	public PriceDTO findValidWithQuantity(PriceWithQuantityRequest priceRequest) {
		PriceDTO priceDto = this.findValid(priceRequest);
		
		log.info("QUANTITY recieve: {}", priceRequest.getQuantity());
		
		Double newValue = priceDto.getValue() * priceRequest.getQuantity();
		
//		DecimalFormat df = new DecimalFormat("####0.00");
//		System.out.println("Value: " + df.format(value));
		
		priceDto.setValue((Math.round(newValue * 100d) / 100d));
		
		return priceDto;
	}
	
}
