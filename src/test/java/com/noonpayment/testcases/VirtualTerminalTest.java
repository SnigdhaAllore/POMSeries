package com.noonpayment.testcases;

import com.noonpayment.pages.VirtualTerminal;
import com.noonpayment.base.TestBase;
import com.noonpayment.util.TestUtil;
import com.noonpayment.util.Constants;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VirtualTerminalTest extends TestBase {

	private VirtualTerminal virtualTerminalObj;
	@BeforeClass
	public void objectInitialization() {
		virtualTerminalObj=new VirtualTerminal(getDriver(),environment);
	}

	@DataProvider
	public Object[][] getCardTestData() {
		return TestUtil.getTestData(Constants.VIRTUAL_DATA);
	}

	@Test(priority = 1, dataProvider = "getCardTestData", enabled = true)
	@Story("Story Name: To Validate VirtualTerminal in Merchant Portal")
	public void merchantPortal_validateVirtualTerminalTest(String item, String amount, String currency, String category,String operation,String expectedStatus) {
		String output = virtualTerminalObj.getVirtualTerminalStatus(item, amount, currency, category, operation);
		Assert.assertEquals(output, expectedStatus);
	}
}