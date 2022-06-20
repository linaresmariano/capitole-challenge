package com.inditex.pricing.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.pricing.dto.PriceDTO;
import com.inditex.pricing.dto.PriceRequest;
import com.inditex.pricing.service.interfaces.IPriceService;

@RestController
@RequestMapping(value = "/prices")
public class PriceController {
	
	@Autowired
	private IPriceService priceService;

	@GetMapping("/valid")
	public ResponseEntity<PriceDTO> find(@Valid PriceRequest priceRequest) {
		return ResponseEntity.ok(priceService.findValid(priceRequest));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PriceDTO> findById(@Valid @PathVariable Long id) {
		return ResponseEntity.ok(priceService.findById(id));
	}

}
