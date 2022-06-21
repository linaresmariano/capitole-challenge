package com.inditex.pricing.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.inditex.pricing.model.CurrencyISO;
import com.inditex.pricing.model.Price;


@DataJpaTest
class PriceRepositoryCRUDTest {

	@Autowired
	private PriceRepository priceRepository;
	
	private final Long BRAND_ID = 40L;
	
	@Test
	void givenPrice_whenSave_thenGetOk() {
		
		Price price = buildGenericPrice();
		
		priceRepository.saveAndFlush(price);
		
		final Long ID = price.getId();
		
		// Price tiene ID
		assertThat(ID).isNotNull();
	
		// Al buscarlo en el repo, se encuentra
		assertThat(priceRepository.findById(ID))
		.isPresent().get()
		.hasFieldOrPropertyWithValue("id", ID)
		.hasFieldOrPropertyWithValue("brandId", BRAND_ID)
		.hasFieldOrPropertyWithValue("versionNumber", 0L);

	}
	
	@Test
	void givenIncompletePrice_whenSave_thenGetError() {
		
		Price price = Price.builder()
				.brandId(BRAND_ID)
				.startDate(LocalDateTime.now())
				.endDate(LocalDateTime.now())
				.productId(9L)
				.curr(CurrencyISO.EUR)
				.build();
		
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			priceRepository.save(price);
		});
		
	}
	
	@Test
	void givenPrice_whenUpdate_thenGetOk() {
		
		Price price = buildGenericPrice();
		priceRepository.saveAndFlush(price);
		
		final long ID = price.getId();
		
		Price savedPrice = priceRepository.findById(ID).get();
		savedPrice.setPriority(20);
		priceRepository.saveAndFlush(savedPrice);
		
		// Al actualizarlo cambia el version number
		assertThat(priceRepository.findById(ID))
		.isPresent().get()
		.hasFieldOrPropertyWithValue("brandId", BRAND_ID)
		.hasFieldOrPropertyWithValue("priority", 20)
		.hasFieldOrPropertyWithValue("versionNumber", 1L);
	}
	
	public Price buildGenericPrice() {
		return Price.builder()
					.brandId(BRAND_ID)
					.startDate(LocalDateTime.now())
					.endDate(LocalDateTime.now())
					.productId(9L)
					.priority(1)
					.priceValue(40D)
					.curr(CurrencyISO.EUR)
					.build();
	}
	
}
