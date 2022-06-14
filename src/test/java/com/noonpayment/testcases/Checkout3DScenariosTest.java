package com.noonpayment.testcases;

import com.noonpayment.pages.Checkout3DScenarios;
import com.noonpayment.base.TestBase;
import com.noonpayment.util.TestUtil;
import com.noonpayment.util.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Checkout3DScenariosTest extends TestBase {

	private Checkout3DScenarios checkOutObj;

	@BeforeClass
	public void objectInitialization() {
		checkOutObj=new Checkout3DScenarios(getDriver());
	}

	@DataProvider
	public Object[][] getCardTestData() {
		return TestUtil.getTestData(Constants.CARDDETAILS_DATA);
	}

	@Test(priority = 1, dataProvider = Constants.GET_CARD_TEST_DATA, enabled = true)
	public void checkoutPro_validate3DScenarioInHostedMode(String cno, String expiry, String cvv) {
		String output = checkOutObj.getHosted3DPageCheckoutStatus(cno, expiry, cvv);
		Assert.assertEquals(output, Constants.AUTHORIZED);
	}
	
	@Test(priority = 2, dataProvider = Constants.GET_CARD_TEST_DATA, enabled = true)
	public void checkoutPro_validate3DScenarioInIframeMode(String cno, String expiry, String cvv) {
		String output = checkOutObj.getIFrame3DPageCheckoutStatus(cno, expiry, cvv);
		Assert.assertEquals(output, Constants.AUTHORIZED);
	}
}