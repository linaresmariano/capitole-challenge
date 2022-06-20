package com.inditex.pricing.dto;

import java.time.LocalDateTime;

import com.inditex.pricing.model.CurrencyISO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceDTO {
	
	private Long id;
	
	private Long brandId;
	
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	private Double value;
	private CurrencyISO curr;
	
}
