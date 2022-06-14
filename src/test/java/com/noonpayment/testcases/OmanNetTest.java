package com.noonpayment.testcases;

import com.noonpayment.pages.OmanNet;
import com.noonpayment.base.TestBase;
import com.noonpayment.util.Constants;
import com.noonpayment.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OmanNetTest extends TestBase{
	OmanNet omanNetObj;
	@BeforeClass
	public void objectInitialization() {
		omanNetObj=new OmanNet(getDriver());
	}
	@DataProvider
	public Object[][] getCardTestData() {
		return TestUtil.getTestData(Constants.OMAN_DETAILS_DATA);
	}
	@Test(priority = 1, dataProvider = Constants.GET_CARD_TEST_DATA, enabled = true)
	public void checkoutPro_validateOmanNetCheckOutTest(String cno, String expiry, String cvv) {
		String output = omanNetObj.getOmanNetCheckoutStatus(cno, expiry, cvv);
		Assert.assertEquals(output,"CAPTURED");
	}
}
