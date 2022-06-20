package com.inditex.pricing.interview;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import com.inditex.pricing.InditexPricingApplication;
import com.inditex.pricing.dto.PriceDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InditexPricingApplication.class,
	webEnvironment = WebEnvironment.RANDOM_PORT)
public class InterviewApplicationTest {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	private String urlTemplate;
	private final Long ZARA_ID = 1L;
	private final Long PRODUCT_ID = 35455L;
	

	private String getURLBase() {
		return "http://localhost:" + port + "/ms-pricing/prices/valid";
	}

	@Before
	public void setUp() {
		urlTemplate = UriComponentsBuilder.fromHttpUrl(getURLBase())
				.queryParam("applicationDate", "{applicationDate}")
				.queryParam("brandId", "{brandId}")
				.queryParam("productId", "{productId}")
				.encode().toUriString();
	}
	
	public void assertIsExpectedPrice(String time, Long expectedPriceId) {
		
		final LocalDateTime localDate = LocalDateTime.parse(time);

		assertThat(restTemplate.getForObject(urlTemplate, PriceDTO.class,
						localDate, ZARA_ID, PRODUCT_ID)
		.getId()).isEqualTo(expectedPriceId);
		
	}
	
	@Test
	public void givenJune14at10h_whenFindValidPrice_thenGet1() {
		String time = "2020-06-14T10:00:00";
		
		this.assertIsExpectedPrice(time, 1L);
	}
	
	@Test
	public void givenJune14at16h_whenFindValidPrice_thenGet2() {
		this.assertIsExpectedPrice("2020-06-14T16:00:00", 2L);
	}
	
	@Test
	public void givenJune14at21h_whenFindValidPrice_thenGet1() {
		this.assertIsExpectedPrice("2020-06-14T21:00:00", 1L);
	}
	
	@Test
	public void givenJune15at10h_whenFindValidPrice_thenGet3() {
		this.assertIsExpectedPrice("2020-06-15T10:00:00", 3L);
	}
	
	@Test
	public void givenJune16at21h_whenFindValidPrice_thenGet4() {
		this.assertIsExpectedPrice("2020-06-16T21:00:00", 4L);
	}
	
}
