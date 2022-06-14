package com.noonpayment.pages;

import com.noonpayment.base.Environment;
import com.noonpayment.util.Constants;
import com.noonpayment.util.ElementUtil;
import com.noonpayment.util.JavaScriptUtil;
import com.noonpayment.util.TimeUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VirtualTerminal {

	private final By username = By.id("UserName");
	private final By password = By.id("Password");
	private final By loginbtn = By.id("loginBtn");
	private final By virtualTerm = By.xpath("//span[contains(text(),'Virtual Terminal')]");
	private final By itemName = By.id("ItemName");
	private final By amount = By.id("Amount");
	private final By orderCategory = By.id("OrderCategory");
	private final By currency = By.id("Currency");
	private final By operationType = By.id("OperationType");
	private final By checkOut = By.xpath("//button[@id='btnCheckout']");
	private final By cardNumber = By.id("txtNewCardFormCardNumber");
	private final By expiryDate = By.id("txtNewCardFormExpiryDate");
	private final By cardCvv = By.id("txtNewCardFormCvv");
	private final By payNow = By.id("btnPayWithCard");
	private final By submitFrame = By.id("redirectTo3ds1Frame");
	private final By submit = By.xpath("//input[@value='Submit']");
	private final By paymentDetails = By.xpath("//div[@class='col-lg-8 col-lg-offset-2']//div[@class='col-lg-12']");

	private final ElementUtil elementUtil;
	private final JavaScriptUtil jsUtil;
	private final Environment environment;
	public VirtualTerminal(WebDriver driver,Environment environment) {
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
		this.environment=environment;
	}

	@Step("Placing an order with ItemN : {0} Amount : {1} Currency : {2} OrderCategory : {3} Operation : {4} step...")
	public String getVirtualTerminalStatus(String item, String amountVal, String currencyVal, String categoryVal,String operation) {
		elementUtil.navigateToUrl(environment.getMerchantURL());
		TimeUtil.mediumWait();
		jsUtil.waitAndClickByJS(username,Constants.WAIT_TIME);
		elementUtil.doSendKeys(username, environment.getMerchantUserName());
		elementUtil.doSendKeys(password, environment.getMerchantPassword());
		elementUtil.doClick(loginbtn);
		jsUtil.waitAndClickByJS(virtualTerm,Constants.WAIT_TIME);
		jsUtil.waitAndClickByJS(itemName,Constants.WAIT_TIME);
		elementUtil.doSendKeys(itemName, item);
		elementUtil.doSendKeys(amount, amountVal);
		elementUtil.doSelectDropDownByValue(orderCategory,categoryVal);
		elementUtil.doSelectDropDownByValue(currency,currencyVal);
		elementUtil.doSelectDropDownByValue(operationType,operation);
		jsUtil.waitAndClickByJS(checkOut,Constants.WAIT_TIME);
		elementUtil.waitForFrameAndSwitchToIt(2,Constants.WAIT_TIME);
		TimeUtil.mediumWait();
		jsUtil.waitAndClickByJS(cardNumber,Constants.WAIT_TIME);
		elementUtil.doSendKeys(cardNumber,"4000000000000002");
		elementUtil.doSendKeys(expiryDate,"12/23");
		elementUtil.doSendKeys(cardCvv,"234");
		elementUtil.doClick(payNow);
		TimeUtil.mediumWait();
		return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails,Constants.WAIT_TIME);
	}
}