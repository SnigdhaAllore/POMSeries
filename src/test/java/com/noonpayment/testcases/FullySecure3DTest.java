package com.noonpayment.testcases;

import com.noonpayment.pages.FullySecure3D;
import com.noonpayment.base.TestBase;
import com.noonpayment.util.Constants;
import com.noonpayment.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FullySecure3DTest extends TestBase {

	private FullySecure3D fullySecure3DObj;

	@BeforeClass
	public void objectInitialization() {
		fullySecure3DObj=new FullySecure3D(getDriver());
	}
	@DataProvider
	public Object[][] getCardTestData() {
		return TestUtil.getTestData(Constants.FULLYSECURE3D_DATA);
	}

	@Test(priority = 1, dataProvider = Constants.GET_CARD_TEST_DATA, enabled = true)
	public void checkoutPro_validateFullySecure3D(String cno, String expiry, String cvv,String type) {
		String output = fullySecure3DObj.getHostedFullySecure3DPageCheckoutByType(cno, expiry, cvv,type);
		if("TypeA".equalsIgnoreCase(type) || "TypeB".equalsIgnoreCase(type))
			Assert.assertEquals(output, Constants.AUTHORIZED);
		else if("TypeC".equalsIgnoreCase(type) || "TypeD".equalsIgnoreCase(type))
			Assert.assertEquals(output, Constants.FAILED);
	}
}