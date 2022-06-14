package com.noonpayment.testcases;

import com.noonpayment.pages.CheckOutPageInArabic;
import com.noonpayment.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckOutPageInArabicTest extends TestBase {

	private CheckOutPageInArabic pageInArabic;

	@BeforeClass
	public void objectInitialization() {
		pageInArabic=new CheckOutPageInArabic(getDriver());
	}
	@Test(priority = 1, enabled = true)
	public void checkoutPro_validateArabicPageLightBoxTest() {
		String arabicText = pageInArabic.getArabicInLightBoxCheckoutStatus();
		Assert.assertEquals(arabicText, "ادفع الآن");
	}
	@Test(priority = 2, enabled = true)
	public void checkoutPro_validateArabicPageHostedModeTest() {
		String arabicText = pageInArabic.getArabicInhostedPageCheckoutStatus();
		Assert.assertEquals(arabicText, "ادفع الآن");
	}
}