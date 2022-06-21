package com.inditex.pricing.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.inditex.pricing.dto.PriceDTO;
import com.inditex.pricing.exception.PriceNotFoundException;
import com.inditex.pricing.exception.RestResponseEntityExceptionHandler;
import com.inditex.pricing.service.interfaces.IPriceService;


@EnableWebMvc
@ExtendWith(MockitoExtension.class)
class PricingControllerTest {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());

	private MockMvc mockMvc;
	
	@Mock
	private IPriceService priceService;
	
	@InjectMocks
	private PriceController priceController;
	
	private final Long BRAND_ID_MOCK = 1L;
	private final String VALID_DATETIME = "2022-06-17T00:00:00";
	private final String INVALID_DATETIME = "20-06-17";
	
	private final String PRICE_OK_PARAMS = "/prices/valid?applicationDate={aDate}&productId={aProduct}&brandId={aBrand}"; 
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(priceController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
	}
	
	private PriceDTO createPriceDTOMock() {
		return PriceDTO.builder()
				.brandId(BRAND_ID_MOCK).id(1L)
				.build();
	}

	@Test
	void whenFindIsOk() throws Exception {
		
		// Al buscar devuelve un mock de priceDTO
		when(priceService.findValid(any()))
			.thenReturn(createPriceDTOMock());
		
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.get(PRICE_OK_PARAMS, VALID_DATETIME, 1, 1)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.OK.value()))
				.andReturn();
		
		assertThat(result.getResponse().getContentAsString())
		.contains("\"brandId\":"+BRAND_ID_MOCK);
		
	}
	
	@Test
	void whenFindWithNotFoundException_thenNoContent() throws Exception {
		
		// Cuando se busca, simula que lanza la exception
		when(priceService.findValid(any()))
			.thenThrow(new PriceNotFoundException());

		// Se valida que al buscar con parametros correctos no devuelve contenido
		mockMvc.perform(MockMvcRequestBuilders
				.get(PRICE_OK_PARAMS, VALID_DATETIME, 1, 1)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is(HttpStatus.NO_CONTENT.value()));
		
	}
	
	@Test
	void whenFindWithRuntimeException_thenInternalError() throws Exception {
		
		// Cuando se busca, simula que lanza la exception
		when(priceService.findValid(any()))
			.thenThrow(new RuntimeException());

		// Se valida que al buscar con parametros correctos no devuelve contenido
		mockMvc.perform(MockMvcRequestBuilders
				.get(PRICE_OK_PARAMS, VALID_DATETIME, 1, 1)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		
	}
	
	@Test
	void whenFindWithIncorrectParams_thenBadRequest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get(PRICE_OK_PARAMS, INVALID_DATETIME, 1, 1)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
		
	}
	
}
