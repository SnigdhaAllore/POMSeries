package com.noonpayment.testcases;

import com.noonpayment.base.TestBase;
import com.noonpayment.pages.ProductCatalog;
import com.noonpayment.util.Constants;
import com.noonpayment.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class ProductCatalogTest extends TestBase {

	private ProductCatalog productObj;

	@BeforeClass
	public void objectInitialization() {
		productObj=new ProductCatalog(getDriver());
	}
	@DataProvider
	public Object[][] getCardTestData() {
		return TestUtil.getTestData(Constants.CARDDETAILS_DATA);
	}

	@Test(priority = 1, dataProvider = Constants.GET_CARD_TEST_DATA, enabled = true)
	public void checkoutPro_validateProdCatalogCheckOutTest(String cno, String expiry, String cvv) {
		String output = productObj.getProductCatalogCheckoutStatus(cno, expiry, cvv);
		Assert.assertEquals( output, Constants.AUTHORIZED);
	}

	@Test(priority = 2, dataProvider = Constants.GET_CARD_TEST_DATA, enabled = true)
	public void checkoutPro_validateHostedPageCheckOutTest(String cno, String expiry, String cvv) {
		String output = productObj.getHostedPageCheckoutStatus(cno, expiry, cvv);
		Assert.assertEquals(output, Constants.AUTHORIZED );
	}

	@Test(priority = 3, dataProvider = Constants.GET_CARD_TEST_DATA, enabled = true)
	public void checkoutPro_validateLightBoxCheckOutTest(String cno, String expiry, String cvv) {
		String output = productObj.getLightBoxCheckoutStatus(cno, expiry, cvv);
		Assert.assertEquals( output, Constants.AUTHORIZED);
	}

	@Test(priority = 4, dataProvider = Constants.GET_CARD_TEST_DATA, enabled = true)
	public void checkoutPro_validateFrameCheckOutTest(String cno, String expiry, String cvv) {
		String output = productObj.getFrameCheckoutStatus(cno, expiry, cvv);
		Assert.assertEquals(output, Constants.AUTHORIZED) ;
	}
	@Test(priority = 5, enabled = true)
	public void checkoutPro_validatetokenTest() {
		String output = productObj.getSavedTokenStatus(environment.getToken());
		Assert.assertEquals(output, Constants.AUTHORIZED);
	}
	@Test(priority = 6, dataProvider = Constants.GET_CARD_TEST_DATA, enabled = true)
	public void checkoutPro_validateReturnUrlCheckOutTest(String cno, String expiry, String cvv) {
		boolean output =productObj.getReturnURLPageTitle(cno, expiry, cvv);
		Assert.assertTrue(output);
	}
}