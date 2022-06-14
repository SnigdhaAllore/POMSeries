package com.noonpayment.pages;

import com.noonpayment.base.Environment;
import com.noonpayment.util.Constants;
import com.noonpayment.util.ElementUtil;
import com.noonpayment.util.JavaScriptUtil;
import com.noonpayment.util.TimeUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class PaymentLink{

	private final By username = By.id("UserName");
	private final By password = By.id("Password");
	private final By loginbtn = By.id("loginBtn");
	private final By payLink = By.xpath("//span[contains(text(),'Payment Link')]");
	private final By itemName = By.id("ItemName");
	private final By amount = By.id("Amount");
	private final By categoryInput = By.xpath("//div[@id='Category_chosen']//input");
	private final By category = By.xpath("//div[@id='Category_chosen']");
	private final By operationInput = By.xpath("//div[@id='OperationType_chosen']//input");
	private final By operation= By.id("OperationType_chosen");
	private final By styleInput = By.xpath("//select[@id='Style']");
	private final By style= By.id("Style");
	private final By generateLink = By.xpath("//button[@id='btnCheckout']//span");
	private final By orderLink = By.id("btnDetail");
	private final By urlTextBox = By.xpath("//input[@id='lbLink']");
	private final By cardNumber = By.id("txtNewCardFormCardNumber");
	private final By expiryDate = By.id("txtNewCardFormExpiryDate");
	private final By cardCvv = By.id("txtNewCardFormCvv");
	private final By cardHolderName = By.id("txtNewCardFormCardHolderName");
	private final By payNow = By.id("btnPayWithCard");
	private final By submitFrame = By.id("redirectTo3ds1Frame");
	private final By submit = By.xpath("//input[@value='Submit']");
	private final By secOrder = By.xpath("//*[@id=\"secOrder\"]/div/div[1]");

	private final ElementUtil elementUtil;
	private final JavaScriptUtil jsUtil;
	private final Environment environment;

	public PaymentLink(WebDriver driver,Environment environment) {
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
		this.environment=environment;
	}

	@Step("Placing an order with ItemN : {0} Amount : {1} Currency : {2} OrderCategory : {3} Operation : {4} Style : {5} step...")
	public String getPaymentLinkStatus(String item, String amountVal, String currencyVal, String categoryVal, String operationVal,String styleVal) {
		elementUtil.navigateToUrl(environment.getMerchantURL());
		jsUtil.waitAndClickByJS(username,Constants.WAIT_TIME);
		elementUtil.doSendKeys(username, environment.getMerchantUserName());
		elementUtil.doSendKeys(password, environment.getMerchantPassword());
		elementUtil.doClick(loginbtn);
		jsUtil.waitAndClickByJS(payLink,Constants.WAIT_TIME);
		elementUtil.doSendKeys(itemName, item);
		elementUtil.doSendKeys(amount, amountVal);
		elementUtil.doKeysReturn(amount, Keys.RETURN);
		TimeUtil.mediumWait();
		elementUtil.doActionsMoveToElementAndClick(category);
		elementUtil.sendKeys(categoryInput, categoryVal);
		elementUtil.doKeysReturn(categoryInput, Keys.RETURN);
		elementUtil.doActionsMoveToElementAndClick(operation);		
		elementUtil.doSendKeys(operationInput, operationVal);
		elementUtil.doKeysReturn(operationInput, Keys.DOWN);
		elementUtil.doKeysReturn(operationInput, Keys.ENTER);		
		jsUtil.waitAndClickByJS(style,Constants.WAIT_TIME);
		elementUtil.sendKeys(styleInput, styleVal);
		jsUtil.waitAndClickByJS(generateLink,10);
		String url=elementUtil.waitGetAttributeValue(urlTextBox);
		elementUtil.openNewTab(url);
		jsUtil.waitAndClickByJS(cardNumber,Constants.WAIT_TIME);
		elementUtil.doSendKeys(cardNumber,"4000000000000002");
		elementUtil.doSendKeys(expiryDate,"12/23");
		elementUtil.doSendKeys(cardCvv,"234");
		elementUtil.doSendKeys(cardHolderName, "Test User");
		elementUtil.doClick(payNow);
		TimeUtil.mediumWait();
		elementUtil.closeAndSwithToMainWindow();
		TimeUtil.mediumWait();
		elementUtil.doClick(orderLink);
		elementUtil.swithToChildWindow();
		String status=elementUtil.waitForElementPresentAndGetStatusTextByIndex(secOrder,1,Constants.WAIT_TIME);
		elementUtil.closeAndSwithToMainWindow();
		return status;
	}
}