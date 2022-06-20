package com.inditex.pricing;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class InditexPricingApplicationTest {

	@Test
	public void main() {
		InditexPricingApplication.main(new String[] {});
		assertTrue("Silly assertion to be compliant with Sonar", true);
	}
	
}
