package com.noonpayment.pages;

import com.noonpayment.util.Constants;
import com.noonpayment.util.ElementUtil;
import com.noonpayment.util.JavaScriptUtil;
import com.noonpayment.util.TimeUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FullySecure3D {
	private final By customPages = By.xpath("(//a[@href='#']/span[1])[3]");
	private final By customCheckOut = By.xpath("//a[@href='/Checkout']");
	private final By amount = By.id("Amount");
	private final By orderName = By.id("ProductName");
	private final By category = By.id("Category");
	private final By checkoutPageMode = By.id("CheckoutPageMode");
	private final By submitFrame = By.id("redirectTo3ds1Frame");
	private final By checkOut = By.id("btnCheckout");
	private final By cardNumber = By.id("txtNewCardFormCardNumber");
	private final By expiryDate = By.id("txtNewCardFormExpiryDate");
	private final By cardCvv = By.id("txtNewCardFormCvv");
	private final By payNow = By.id("btnPayWithCard");
	private final By submit = By.xpath("//input[@value='Submit']");
	private final By paymentDetails = By.xpath(".//*[@id='page-wrapper']/div[2]/div/div/div[1]/div");
	private final By activateNow = By.xpath("//input[@value='Activate Now']");
	private final By exit = By.xpath("//a[contains(text(),'Exit')]");
	private final By continueBtn = By.xpath(" //input[@name='Submit']");

	private final ElementUtil elementUtil;
	private final JavaScriptUtil jsUtil;

	public FullySecure3D(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}

	public String getHostedFullySecure3DPageCheckoutByType(String cno, String expiry, String cvv, String type) {
		customCheckoutHostedFlow(cno, expiry, cvv, 1);
		if ("TypeA".equalsIgnoreCase(type))
			elementUtil.clickWhenReady(submit, Constants.WAIT_TIME);
		else if ("TypeB".equalsIgnoreCase(type))
			elementUtil.clickWhenReady(activateNow, Constants.WAIT_TIME);
		else if ("TypeC".equalsIgnoreCase(type)) {
			elementUtil.clickWhenReady(exit, Constants.WAIT_TIME);
			elementUtil.acceptAlert(Constants.WAIT_TIME);
		}
		else if ("TypeD".equalsIgnoreCase(type))
			elementUtil.clickWhenReady(continueBtn, Constants.WAIT_TIME);
		TimeUtil.shortWait();
		return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails, Constants.WAIT_TIME);
	}

	public void customCheckoutHostedFlow(String cno, String expiry, String cvv, int pageMode) {
		jsUtil.waitAndClickByJS(customPages, Constants.WAIT_TIME);
		jsUtil.waitAndClickByJS(customCheckOut, Constants.WAIT_TIME);
		elementUtil.doSendKeys(amount, "75");
		elementUtil.doSendKeys(orderName, "test");
		elementUtil.doSendKeys(category, "secure");
		elementUtil.doSelectDropDownByIndex(checkoutPageMode, pageMode);
		jsUtil.clickByJS(checkOut);
		elementUtil.clickWhenReady(cardNumber, Constants.WAIT_TIME);
		elementUtil.doSendKeys(cardNumber, cno);
		elementUtil.doSendKeys(expiryDate, expiry);
		elementUtil.doSendKeys(cardCvv, cvv);
		elementUtil.doClick(payNow);
		elementUtil.waitForFrameAndSwitchToIt(submitFrame, Constants.WAIT_TIME);
	}

}