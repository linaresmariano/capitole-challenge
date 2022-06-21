package com.inditex.pricing.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import com.inditex.pricing.dto.PriceDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FindValidPriceIntegrationTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	private String urlTemplate;
	private final Long BRAND_ID = 1L;
	private final Long PRODUCT_ID = 35455L;
	

	private String getURLBase() {
		return "http://localhost:" + port + "/ms-pricing/prices/valid";
	}

	@BeforeEach
	public void setUp() {
		urlTemplate = UriComponentsBuilder.fromHttpUrl(getURLBase())
				.queryParam("applicationDate", "{applicationDate}")
				.queryParam("brandId", "{brandId}")
				.queryParam("productId", "{productId}")
				.encode().toUriString();
	}
	
	public void assertIsExpectedPrice(String dateTimeIso, Long expectedPriceId) {
		
		final LocalDateTime localDate = LocalDateTime.parse(dateTimeIso);

		assertThat(restTemplate.getForObject(urlTemplate, PriceDTO.class,
						localDate, BRAND_ID, PRODUCT_ID)
		.getId()).isEqualTo(expectedPriceId);
		
	}
	
	@Test
	void testFindPriceByTest() {
		this.assertIsExpectedPrice("2020-06-14T00:00:00", 1L);
	}
	
	@Test
	void testFindPriceByTest2() {
		this.assertIsExpectedPrice("2020-06-14T15:00:00", 2L);
	}
	
}
