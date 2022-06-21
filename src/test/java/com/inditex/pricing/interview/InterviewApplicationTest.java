package com.inditex.pricing.interview;

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
class InterviewApplicationTest {
	
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

	@BeforeEach
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
	void givenJune14at10h_whenFindValidPrice_thenGet1() {
		String time = "2020-06-14T10:00:00";
		
		this.assertIsExpectedPrice(time, 1L);
	}
	
	@Test
	void givenJune14at16h_whenFindValidPrice_thenGet2() {
		this.assertIsExpectedPrice("2020-06-14T16:00:00", 2L);
	}
	
	@Test
	void givenJune14at21h_whenFindValidPrice_thenGet1() {
		this.assertIsExpectedPrice("2020-06-14T21:00:00", 1L);
	}
	
	@Test
	void givenJune15at10h_whenFindValidPrice_thenGet3() {
		this.assertIsExpectedPrice("2020-06-15T10:00:00", 3L);
	}
	
	@Test
	void givenJune16at21h_whenFindValidPrice_thenGet4() {
		this.assertIsExpectedPrice("2020-06-16T21:00:00", 4L);
	}
	
}
