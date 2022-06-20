package com.inditex.pricing.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.inditex.pricing.model.CurrencyISO;
import com.inditex.pricing.model.Price;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PriceRepositoryCRUDTest {

	@Autowired
	private PriceRepository priceRepository;
	
	private final Long BRAND_ID = 40L;
	
	@Test
	public void givenPrice_whenSave_thenGetOk() {
		
		Price price = Price.builder()
				.brandId(BRAND_ID)
				.startDate(LocalDateTime.now())
				.endDate(LocalDateTime.now())
				.productId(9L)
				.priority(1)
				.priceValue(40D)
				.curr(CurrencyISO.EUR)
				.build();
		
		priceRepository.save(price);
		
		final Long ID = price.getId();
		
		// Price tiene ID
		assertThat(ID).isNotNull();
	
		// Al buscarlo en el repo, se encuentra
		assertThat(priceRepository.findById(ID))
		.isPresent()
		.get()
		.hasFieldOrPropertyWithValue("id", ID)
		.hasFieldOrPropertyWithValue("brandId", BRAND_ID);
	}
	
	@Test
	public void givenIncompletePrice_whenSave_thenGetError() {
		
		Price price = Price.builder()
				.brandId(BRAND_ID)
				.startDate(LocalDateTime.now())
				.endDate(LocalDateTime.now())
				.productId(9L)
				.curr(CurrencyISO.EUR)
				.build();
		
		assertThatExceptionOfType(ConstraintViolationException.class)
		.isThrownBy(() -> {
			priceRepository.save(price);
		})
		.withMessageContaining("Validation failed for classes [com.inditex.pricing.model.Price]");
		
	}
	
}
