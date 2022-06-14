package com.noonpayment.testcases;

import com.noonpayment.pages.Checkout3DS2Scenarios;
import com.noonpayment.base.TestBase;
import com.noonpayment.util.Constants;
import com.noonpayment.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Checkout3DS2ScenariosTest extends TestBase {

	private Checkout3DS2Scenarios checkouts3ds2Obj;

	@BeforeClass
	public void objectInitialization() {
		checkouts3ds2Obj = new Checkout3DS2Scenarios(getDriver());
	}

	@DataProvider
	public Object[][] getCardTestData_3DS2Cybs() {
		return TestUtil.getTestData(Constants.CYBS3DS2_DATA);
	}

	@DataProvider
	public Object[][] getCardTestData() {
		return TestUtil.getTestData(Constants.MPGS3DS2_DATA);
	}

	@Test(priority = 1, dataProvider = Constants.GET_3DS2CARD_TEST_DATA, enabled = true)
	public void checkoutPro_validateCheckout3DS2CybsTest(String cno, String expiry, String cvv, String type) {
		String output = checkouts3ds2Obj.getHosted3DS2CybsPageCheckoutByType(cno, expiry, cvv, type);
		if ("TypeA".equalsIgnoreCase(type))
			Assert.assertEquals(output, Constants.AUTHORIZED);
		else if ("TypeB".equalsIgnoreCase(type) || "TypeC".equalsIgnoreCase(type))
			Assert.assertEquals(output, Constants.FAILED);

	}

	@Test(priority = 1, dataProvider = Constants.GET_CARD_TEST_DATA, enabled = true)
	public void checkoutPro_validateCheckout3DS2MpgsTest(String cno, String expiry, String cvv, String type) {
		String output = checkouts3ds2Obj.getHosted3DS2MpgsPageCheckoutByType(cno, expiry, cvv, type);
		if ("TypeA".equalsIgnoreCase(type))
			Assert.assertEquals(output, Constants.AUTHORIZED);
		else if ("TypeB".equalsIgnoreCase(type) || "TypeC".equalsIgnoreCase(type))
			Assert.assertEquals(output, Constants.AUTHORIZED);

	}
}
