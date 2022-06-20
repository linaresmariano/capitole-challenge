package com.inditex.pricing.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.inditex.pricing.model.Price;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PriceRepositoryTest {

	@Autowired
	private PriceRepository priceRepository;
	
	private final Long PRODUCT_ID = 35455L;
	private final Long BRAND_ID = 1L;
	

	public void assertIsExpectedPrice(String time, Long expectedPriceId) {
		
		final LocalDateTime date = LocalDateTime.parse(time);

		Optional<Price> price =
				priceRepository.getValidPrice(date, PRODUCT_ID, BRAND_ID);
		
		assertThat(price).isPresent()
		.get()
		.hasFieldOrPropertyWithValue("id", expectedPriceId)
		.hasFieldOrPropertyWithValue("brandId", BRAND_ID);
		
	}
	
	public void assertNoPrice(String time) {
		
		final LocalDateTime date = LocalDateTime.parse(time);
		
		assertThat(priceRepository.getValidPrice(date, PRODUCT_ID, BRAND_ID))
		.isNotPresent();
		
	}
	
	@Test
	public void givenJune13at23h59m59s_whenFindValidPrice_thenNoContent() {
		this.assertNoPrice("2020-06-13T23:59:59");
	}
	
	@Test
	public void givenJune14at00h00m_whenFindValidPrice_thenGet1() {
		this.assertIsExpectedPrice("2020-06-14T00:00:00", 1L);
	}
	
	@Test
	public void givenJune14at14h59m_whenFindValidPrice_thenGet1() {
		this.assertIsExpectedPrice("2020-06-14T14:59:59", 1L);
	}
	
	@Test
	public void givenJune14at15h00m_whenFindValidPrice_thenGet2() {
		this.assertIsExpectedPrice("2020-06-14T15:00:00", 2L);
	}
	
	@Test
	public void givenJune14at18h30m_whenFindValidPrice_thenGet2() {
		this.assertIsExpectedPrice("2020-06-14T18:30:00", 2L);
	}
	
	@Test
	public void givenJune14at18h30m01s_whenFindValidPrice_thenGet1() {
		this.assertIsExpectedPrice("2020-06-14T18:30:01", 1L);
	}
	
	@Test
	public void givenJune14at23h59m59s_whenFindValidPrice_thenGet1() {
		this.assertIsExpectedPrice("2020-06-14T23:59:59", 1L);
	}
	
	@Test
	public void givenJune15at00h00m_whenFindValidPrice_thenGet3() {
		this.assertIsExpectedPrice("2020-06-15T00:00:00", 3L);
	}
	
	@Test
	public void givenJune15at11h00m_whenFindValidPrice_thenGet3() {
		this.assertIsExpectedPrice("2020-06-15T11:00:00", 3L);
	}
	
	@Test
	public void givenJune15at11h00m01s_whenFindValidPrice_thenGet1() {
		this.assertIsExpectedPrice("2020-06-15T11:00:01", 1L);
	}
	
	@Test
	public void givenJune15at15h59m59s_whenFindValidPrice_thenGet1() {
		this.assertIsExpectedPrice("2020-06-15T15:59:59", 1L);
	}
	
	@Test
	public void givenJune15at16h00m_whenFindValidPrice_thenGet4() {
		this.assertIsExpectedPrice("2020-06-15T16:00:00", 4L);
	}
	
	@Test
	public void givenDec31at23h59m59s_whenFindValidPrice_thenGet4() {
		this.assertIsExpectedPrice("2020-12-31T23:59:59", 4L);
	}
	
	@Test
	public void givenJan01at00h00m_whenFindValidPrice_thenNoContent() {
		this.assertNoPrice("2021-01-01T00:00:00");
	}
	
}
