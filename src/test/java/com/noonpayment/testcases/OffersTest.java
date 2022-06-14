package com.noonpayment.testcases;

import com.noonpayment.pages.Offers;
import com.noonpayment.base.TestBase;
import com.noonpayment.util.TestUtil;
import com.noonpayment.util.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OffersTest extends TestBase {
	private Offers offersObj;
	@BeforeClass
	public void objectInitialization() {
		offersObj=new Offers(getDriver());
	}

	@DataProvider
	public Object[][] getCardTestData() {
		return TestUtil.getTestData(Constants.OFFER_DETAILS_DATA);
	}
	@Test(priority = 1, dataProvider = Constants.GET_CARD_TEST_DATA, enabled = true)
	public void checkoutPro_validateOffersTest(String cno, String expiry, String cvv) {
		String output = offersObj.getOffersStatus(cno, expiry, cvv);
		Assert.assertEquals(output, Constants.AUTHORIZED);
	}
	
	@Test(priority = 2, dataProvider = Constants.GET_CARD_TEST_DATA, enabled = true)
	public void checkoutPro_validateOffersExemptedAmountTest(String cno, String expiry, String cvv) {
		String output = offersObj.getOfferExemptedAmountText(cno, expiry, cvv);
		Assert.assertEquals(output,"** Amount applicable for discount");
	}
}


