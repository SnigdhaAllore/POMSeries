package com.noonpayment.pages;

import com.noonpayment.base.Environment;
import com.noonpayment.util.ElementUtil;
import com.noonpayment.util.JavaScriptUtil;
import com.noonpayment.util.TimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GlobalListRules{
	private final By usernamePro = By.id("field_username");
	private final By passwordPro = By.id("field_password");
	private final By submitPro = By.id("submit_button");
	private final By fraudManagement = By.cssSelector("mat-list-item:nth-child(2) span:nth-child(2)");
	private final By globalListRule = By.id("fraud-rule-management-global-list-based-rules");
	private final By addNewRules = By.id("global-list-based-rules-add-rules-button");
	private final By ruleName = By.id("add-edit-list-based-rule-rule-name");
	private final By ruleDescription = By.id("add-edit-list-based-rule-rule-desc");
	private final By ruleValueType = By.id("add-edit-list-based-rule-value-type");
	private final By country = By.xpath("//mat-option[@id='mat-option-5']/span");
	private final By israelSel = By.xpath("//span[contains(.,'Israel')]");
	private final By ruleValSelect = By.id("add-edit-list-based-rule-rule-values");
	private final By ruleValue = By.id("mat-chip-list-input-0");
	private final By status = By.id("add-edit-list-based-rule-status");
	private final By active = By.xpath("//span[contains(text(),'Active')]");
	private final By save = By.id("add-edit-list-based-rule-save");
	private final By ruleNameSearch = By.id("global-list-based-rules-search-criteria-rule-name");
	private final By ruleNameSearchButton = By.xpath("//button[@id='global-list-based-rules-search-criteria-search-button']/span");
	private final By addToCart = By.xpath("//a[@href='/Shopping/ShoppingCart?id=1']");
	private final By checkOut = By.id("btnCheckout");
	private final By useANewCard = By.xpath("//label[contains(.,'Use a new card')]");
	private final By cardNumber = By.id("txtNewCardFormCardNumber");
	private final By expiryDate = By.id("txtNewCardFormExpiryDate");
	private final By cardCvv = By.id("txtNewCardFormCvv");
	private final By payNow = By.id("btnPayWithCard");
	private final By paymentDetails = By.xpath(".//*[@id='page-wrapper']/div[2]/div/div/div[1]/div");
	private final By deleteForever = By.xpath("//mat-icon[contains(.,'delete_forever')]");
	private final By yesBtn = By.xpath("//span[text()='Yes']/parent::button");
	private final By toastText = By.xpath("//div[@id='toast-container']/div/div");

	private final ElementUtil elementUtil;
	private final Environment environment;
	private final JavaScriptUtil jsUtil;

	public GlobalListRules(WebDriver driver,Environment environment) {
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
		this.environment=environment;
	}
	public String createGlobalListRulesCardBin(String ruleNameValue, String cardBin) {
		elementUtil.navigateToUrl(environment.getMerchantProURL());
		jsUtil.waitAndClickByJS(usernamePro,10);
		elementUtil.doSendKeys(usernamePro, environment.getMerchantProUserName());
		elementUtil.doSendKeys(passwordPro, environment.getMerchantProPassword());
		elementUtil.doClick(submitPro);
		elementUtil.clickWhenReady(fraudManagement,10);
		elementUtil.doClick(globalListRule);
		jsUtil.waitAndClickByJS(ruleNameSearch,10);
		elementUtil.doSendKeys(ruleNameSearch,ruleNameValue);
		jsUtil.waitAndClickByJS(ruleNameSearchButton,10);
		TimeUtil.mediumWait();
		if(elementUtil.verifyTextPresent(ruleNameValue))
			return "Success";
		else {
			elementUtil.clickWhenReady(addNewRules, 10);
			elementUtil.doSendKeys(ruleName, ruleNameValue);
			elementUtil.doSendKeys(ruleDescription, "Rule for Card Bin");
			elementUtil.doSendKeys(ruleValue, cardBin);
			elementUtil.clickWhenReady(status, 10);
			elementUtil.doClick(active);
			jsUtil.waitAndClickByJS(save,10);
			TimeUtil.mediumWait();
			return elementUtil.getElementText(toastText);
		  }
	}

	public String createGlobalListRulesCountry(String ruleNameValue) {
		elementUtil.navigateToUrl(environment.getMerchantProURL());
		jsUtil.waitAndClickByJS(usernamePro,10);
		elementUtil.doSendKeys(usernamePro, environment.getMerchantProUserName());
		elementUtil.doSendKeys(passwordPro, environment.getMerchantProPassword());
		elementUtil.doClick(submitPro);
		elementUtil.clickWhenReady(fraudManagement,10);
		elementUtil.doClick(globalListRule);
		jsUtil.waitAndClickByJS(ruleNameSearch,10);
		elementUtil.doSendKeys(ruleNameSearch,ruleNameValue);
		jsUtil.waitAndClickByJS(ruleNameSearchButton,10);
		TimeUtil.mediumWait();
		  if(elementUtil.verifyTextPresent(ruleNameValue)) {
		  return "Success";
		  }else {
			elementUtil.clickWhenReady(addNewRules, 10);
			elementUtil.doSendKeys(ruleName, ruleNameValue);
			elementUtil.doSendKeys(ruleDescription, "Rule for Country");
			elementUtil.doActionsMoveToElementAndClick(ruleValueType);
			TimeUtil.mediumWait();
			elementUtil.doClick(country);
			elementUtil.doActionsMoveToElementAndClick(ruleValSelect);
			jsUtil.waitAndClickByJS(israelSel,10);
			jsUtil.waitAndClickByJS(status, 10);
			jsUtil.waitAndClickByJS(status, 10);
			jsUtil.waitAndClickByJS(active,10);
		    jsUtil.waitAndClickByJS(save,10);
		    TimeUtil.mediumWait();
		    return elementUtil.getElementText(toastText);
		  }
	}

	public String validateGlobalListRulesDemo(String cno, String expiry, String cvv) {
		jsUtil.clickByJS(addToCart);
		jsUtil.clickByJS(checkOut);
		elementUtil.clickWhenReady(useANewCard,10);
		TimeUtil.shortWait();
		jsUtil.waitAndClickByJS(cardNumber,10);
		elementUtil.doSendKeys(cardNumber,cno);
		elementUtil.doSendKeys(expiryDate,expiry);
		elementUtil.doSendKeys(cardCvv,cvv);
		elementUtil.doKeysReturn(cardCvv, Keys.RETURN);
		jsUtil.waitAndClickByJS(payNow,10);
		return  elementUtil.waitForElementPresentAndGetStatusText(paymentDetails,10);
	}
	public String loginAndDeleteRuleGlobalListRules(String ruleNameValue) {
		elementUtil.navigateToUrl(environment.getMerchantProURL());
		jsUtil.waitAndClickByJS(usernamePro,10);
		elementUtil.doSendKeys(usernamePro, environment.getMerchantProUserName());
		elementUtil.doSendKeys(passwordPro, environment.getMerchantProPassword());
		elementUtil.doClick(submitPro);
		elementUtil.clickWhenReady(fraudManagement,10);
		elementUtil.doClick(globalListRule);
		jsUtil.waitAndClickByJS(ruleNameSearch,10);
		elementUtil.doSendKeys(ruleNameSearch,ruleNameValue);
		elementUtil.doClick(ruleNameSearchButton);
		jsUtil.waitAndClickByJS(deleteForever,10);
		elementUtil.doClick(yesBtn);
		TimeUtil.mediumWait();
		return elementUtil.getElementText(toastText);
	}
}