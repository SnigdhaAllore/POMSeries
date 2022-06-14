package com.noonpayment.testcases;

import com.noonpayment.pages.JSSDK;
import com.noonpayment.base.TestBase;
import com.noonpayment.util.Constants;
import com.noonpayment.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class JSSDKTest extends TestBase {

	private JSSDK JSSDKObj;

	@BeforeClass
	public void objectInitialization() {
		JSSDKObj = new JSSDK(getDriver());
	}

	@DataProvider
	public Object[][] getCardTestData() {
		return TestUtil.getTestData(Constants.CARDDETAILS_DATA);
	}

	@Test(priority = 1, dataProvider = Constants.GET_CARD_TEST_DATA, enabled = true)
	public void checkoutPro_validateJSSDKCheckOutTest(String cno, String expiry, String cvv) {
		String output = JSSDKObj.getJSSDKCheckOutStatus(cno, expiry, cvv);
		Assert.assertEquals(output, Constants.AUTHORIZED);
	}

	
	/*
	 * @Test(priority = 2, dataProvider = GET_CARD_TEST_DATA, enabled = false)
	 * public void checkoutPro_validateJSSDKCheckOutCustomURLTest(String cno, String expiry,
	 * String cvv) { boolean output =
	 * JSSDKObj.getJSSDKCheckOutCustomURLStatus(cno,expiry, cvv);
	 * Assert.assertTrue(output); }
	 */
	 

	@Test(priority = 3, dataProvider = Constants.GET_CARD_TEST_DATA, enabled = true)
	public void checkoutPro_validateJSSDKCheckOut3DStatus(String cno, String expiry, String cvv) {
		String output = JSSDKObj.getJSSDKCheckOut3DStatus(cno, expiry, cvv);
		Assert.assertEquals(output, Constants.AUTHORIZED);
	}

	@Test(priority = 4, dataProvider = Constants.GET_CARD_TEST_DATA, enabled = true)
	public void checkoutPro_validateJSSDKCheckOut3DCustomURLTestStatus(String cno, String expiry, String cvv) {
		String output = JSSDKObj.getJSSDKCheckOut3DCustomURLStatus(cno, expiry, cvv);
		Assert.assertTrue(output.contains("orderId=") && output.contains("paymentType=Card"));		
	}

	@Test(priority = 5, dataProvider = Constants.GET_CARD_TEST_DATA, enabled = true)
	public void checkoutPro_validateJSSDKCheckOut3D2TestStatus(String cno, String expiry, String cvv) {
		String output = JSSDKObj.getJSSDKCheckOut3D2Status(cno, expiry, cvv);
		Assert.assertEquals(output, Constants.AUTHORIZED);
	}

	@Test(priority = 6, dataProvider = Constants.GET_CARD_TEST_DATA, enabled = true)
	public void checkoutPro_validateJSSDKFailedTestStatus(String cno, String expiry, String cvv) {
		String output = JSSDKObj.getJSSDKFailedStatus(cno, expiry, cvv);
		Assert.assertEquals(output, Constants.FAILED);
	}

	@Test(priority = 7, dataProvider = Constants.GET_CARD_TEST_DATA, enabled = true)
	public void checkoutPro_validateJSSDKEMITestStatus(String cno, String expiry, String cvv) {
		String output = JSSDKObj.getJSSDKEMIStatus(cno, expiry, cvv);
		Assert.assertEquals(output, Constants.AUTHORIZED);
	}

}