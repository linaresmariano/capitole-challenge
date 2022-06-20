package com.inditex.pricing.integration;

import static org.assertj.core.api.Assertions.assertThat;

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

import com.inditex.pricing.InditexPricingApplication;
import com.inditex.pricing.dto.PriceDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InditexPricingApplication.class,
	webEnvironment = WebEnvironment.RANDOM_PORT)
//@Sql({ "classpath:data.sql" })
public class FindByIdIntegrationTest {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	private String getURLBase() {
		return "http://localhost:" + port + "/ms-pricing";
	}

	@Before
	public void setUp() {
	}
	
	@Test
	public void testFindPriceById() {
		assertThat(restTemplate.getForObject(getURLBase()+"/prices/1", PriceDTO.class)
		.getId()).isEqualTo(1L);
	}
	
}
