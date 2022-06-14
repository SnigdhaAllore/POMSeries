package com.noonpayment.testcases;

import com.noonpayment.pages.SavedCards;
import com.noonpayment.base.TestBase;
import com.noonpayment.util.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SavedCardsTest extends TestBase {
	private SavedCards savedCardsObj;

	@BeforeClass
	public void objectInitialization() {
		savedCardsObj=new SavedCards(getDriver());
	}

	@Test(priority = 1, enabled = true)
	public void checkoutPro_validateSavedCardHostedTest() {
		String output = savedCardsObj.getSavedCardHostedPageStatus();
		Assert.assertEquals(output, Constants.AUTHORIZED);
	}

	@Test(priority = 2, enabled = true)
	public void checkoutPro_validateSavedCardLightBoxTest() {
		String output = savedCardsObj.getSavedCardLightBoxPageStatus();
		Assert.assertEquals(output, Constants.AUTHORIZED);
	}

	@Test(priority = 3, enabled = true)
	public void checkoutPro_validateSavedCardIframeTest() {
		String output = savedCardsObj.getSavedCardIframePageStatus();
		Assert.assertEquals(output, Constants.AUTHORIZED);
	}
}