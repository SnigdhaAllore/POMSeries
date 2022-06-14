package com.noonpayment.testcases;

import com.noonpayment.pages.PaymentLink;
import com.noonpayment.base.TestBase;
import com.noonpayment.util.TestUtil;
import com.noonpayment.util.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PaymentLinkTest extends TestBase {

	private PaymentLink paymentLinkObj;
	@BeforeClass
	public void objectInitialization() {
		paymentLinkObj=new PaymentLink(getDriver(),environment);
	}

	@DataProvider
	public Object[][] getCardTestData() {
		return TestUtil.getTestData(Constants.PAYMENT_DATA);
	}

	@Test(priority = 1, dataProvider = "getCardTestData", enabled = true)
	public void merchantPortal_validatePaymentLinkTest(String item, String amount, String currency,String category,String operation, String style, String expectedStatus) {
		String output=paymentLinkObj.getPaymentLinkStatus(item, amount, currency, category, operation, style);
		Assert.assertEquals(output, expectedStatus);
	}
}