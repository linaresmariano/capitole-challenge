package com.inditex.pricing.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Builder;
import lombok.Data;

@Data
public class PriceRequest {

	@NotNull
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime applicationDate;
	
	@NotNull
	private Long productId;
	
	@NotNull
	private Long brandId;
	
}
