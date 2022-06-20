package com.inditex.pricing.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.inditex.pricing.exception.RestResponseEntityExceptionHandler;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@EnableWebMvc
@RunWith(JUnitParamsRunner.class)
public class HomeControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private HomeController homeController;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(homeController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler()).build();

	}

	public static Object[] data() {
		return new Object[] {
				new Object[] { "/ping", "Hello from Inditex Pricing ping" },
				new Object[] { "/", "Hello from Inditex Pricing Home" }, };
	}

	@Test
	@Parameters(method = "data")
	public void whenHomeIsOk(String url, String resultTest) throws Exception {
		MvcResult result = mockMvc
			.perform(MockMvcRequestBuilders.get(url))
			.andExpect(status().is(HttpStatus.OK.value()))
			.andReturn();

		assertThat(result.getResponse().getContentAsString()).isEqualTo(resultTest);
	}

}

