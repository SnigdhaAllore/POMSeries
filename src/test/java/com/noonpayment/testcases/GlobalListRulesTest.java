package com.noonpayment.testcases;

import com.noonpayment.pages.GlobalListRules;
import com.noonpayment.base.TestBase;
import com.noonpayment.util.TestUtil;
import com.noonpayment.util.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GlobalListRulesTest extends TestBase {
	private GlobalListRules globalListRulesObj;

	@BeforeClass
	public void objectInitialization() {
		globalListRulesObj=new GlobalListRules(getDriver(),environment);
	}

	@DataProvider
	public Object[][] getCardTestData() {
		return TestUtil.getTestData(Constants.GLOBAL_CARDBIN_DATA);
	}
	
	@DataProvider
	public Object[][] getCardTestData_Country() {
		return TestUtil.getTestData(Constants.GLOBAL_COUNTRY_DATA);
	}

	@Test(priority = 1,dataProvider = Constants.GET_CARD_TEST_DATA, enabled = true)
	public void merchantPortalPro_createGlobalListRulesCardBinTest(String ruleNameValue, String cardBin) {
		String output = globalListRulesObj.createGlobalListRulesCardBin(ruleNameValue,cardBin);
		Assert.assertEquals(output, "Success");
	}
	
	@Test(priority = 1,dataProvider = Constants.GET_CARD_COUNTRY_DATA, enabled = true)
	public void merchantPortalPro_createGlobalListRulesCountryTest(String ruleNameValue) {
		String output = globalListRulesObj.createGlobalListRulesCountry(ruleNameValue);
		Assert.assertEquals(output, "Success");
	}
	@Test(priority = 2,dataProvider = Constants.GET_CARD_TEST_DATA,dependsOnMethods={"merchantPortalPro_createGlobalListRulesCardBinTest"}, enabled = true)
	public void merchantPortalPro_validateGlobalListRulesDemoCardBinTest(String ruleNameValue, String cardBin, String cno, String expiry, String cvv) {
		String output = globalListRulesObj.validateGlobalListRulesDemo(cno,expiry,cvv);
		Assert.assertEquals(output, "REJECTED");
	}
	@Test(priority = 2,dataProvider = Constants.GET_CARD_COUNTRY_DATA,dependsOnMethods={"merchantPortalPro_createGlobalListRulesCountryTest"}, enabled = true)
	public void merchantPortalPro_validateGlobalListRulesDemoCountryTest(String ruleNameValue, String cno, String expiry, String cvv) {
		String output = globalListRulesObj.validateGlobalListRulesDemo(cno,expiry,cvv);
		Assert.assertEquals(output, "REJECTED");
	}
	@Test(priority = 3,dataProvider = Constants.GET_CARD_TEST_DATA,dependsOnMethods={"merchantPortalPro_createGlobalListRulesCardBinTest"},enabled = true)
	public void merchantPortalPro_loginAndDeleteRuleGlobalListRulesCardBinTest(String ruleNameValue, String cardBin, String cno, String expiry, String cvv) {
		String output = globalListRulesObj.loginAndDeleteRuleGlobalListRules(ruleNameValue);
		Assert.assertEquals(output, "Success");
	}
	@Test(priority = 3,dataProvider = Constants.GET_CARD_COUNTRY_DATA, dependsOnMethods={"merchantPortalPro_createGlobalListRulesCountryTest"},enabled = true)
	public void merchantPortalPro_loginAndDeleteRuleGlobalListRulesCountryTest(String ruleNameValue, String cno, String expiry, String cvv) {
		String output = globalListRulesObj.loginAndDeleteRuleGlobalListRules(ruleNameValue);
		Assert.assertEquals(output, "Success");
	}
}
