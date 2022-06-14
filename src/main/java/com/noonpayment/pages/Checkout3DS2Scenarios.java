package com.noonpayment.pages;

import com.noonpayment.util.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.noonpayment.util.ElementUtil;
import com.noonpayment.util.JavaScriptUtil;
import com.noonpayment.util.TimeUtil;

public class Checkout3DS2Scenarios {

	private final By customPages = By.xpath("(//a[@href='#']/span[1])[3]");
	private final By customCheckOut = By.xpath("//a[@href='/Checkout']");
	private final By amount = By.id("Amount");
	private final By orderName = By.id("ProductName");
	private final By category = By.id("Category");
	private final By checkoutPageMode = By.id("CheckoutPageMode");
	private final By submitFrame = By.id("redirectTo3ds1Frame");
	private final By submittitle = By.xpath("//iframe[@id='redirectTo3ds1Frame']");
	private final By checkOut = By.id("btnCheckout");
	private final By cardNumber = By.id("txtNewCardFormCardNumber");
	private final By expiryDate = By.id("txtNewCardFormExpiryDate");
	private final By cardCvv = By.id("txtNewCardFormCvv");
	private final By payNow = By.id("btnPayWithCard");
	private final By submit = By.xpath("//input[@value='Submit']");
	private final By paymentDetails = By.xpath(".//*[@id='page-wrapper']/div[2]/div/div/div[1]/div");
	private final By iframe3d = By.id("challengeFrame");
	private final By acsSubmit = By.id("acssubmit");
	private final By otpFrame = By.xpath("//iframe[contains(@id, 'cardinal-stepUpIframe-')]");
	private final By submit3DS2 = By.xpath("//input[@value='SUBMIT']");
	private final By otp = By.name("challengeDataEntry");

	private final ElementUtil elementUtil;
	private final JavaScriptUtil jsUtil;

	public Checkout3DS2Scenarios(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}

	public String getHosted3DS2CybsPageCheckoutByType(String cno, String expiry, String cvv, String type) {
		customCheckoutHosted3DS2Flow(cno, expiry, cvv, 1, "restapi");
		if ("TypeA".equalsIgnoreCase(type)) {
			clickOn3Ds2Page();
		}
		if ("TypeB".equalsIgnoreCase(type)) {
			clickOn3Ds2Page();
		}
		if ("TypeC".equalsIgnoreCase(type)) {
			clickOn3Ds2Page();
		}
		return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails, Constants.WAIT_TIME);
	}

	public String getHosted3DS2MpgsPageCheckoutByType(String cno, String expiry, String cvv, String type) {
		customCheckoutHosted3DS2Flow(cno, expiry, cvv, 1, "mpgs_3ds");
		if ("TypeA".equalsIgnoreCase(type)) {			
		elementUtil.waitForFrameAndSwitchToIt(iframe3d, Constants.WAIT_TIME);
		elementUtil.clickWhenReady(acsSubmit, Constants.WAIT_TIME);
		TimeUtil.longWait();
		}
		if ("TypeB".equalsIgnoreCase(type)) {
			elementUtil.waitForFrameAndSwitchToIt(submitFrame, Constants.WAIT_TIME);
			jsUtil.clickByJS(submit);
			TimeUtil.longWait();
		}	
		if ("TypeC".equalsIgnoreCase(type)) {
		}
		return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails, Constants.WAIT_TIME);
	}

	public void customCheckoutHosted3DS2Flow(String cno, String expiry, String cvv, int pageMode, String ordCategory) {
		jsUtil.waitAndClickByJS(customPages, 10);
		jsUtil.waitAndClickByJS(customCheckOut, 10);
		elementUtil.doSendKeys(amount, "150");
		elementUtil.doSendKeys(orderName, "test");
		elementUtil.doSendKeys(category, ordCategory);
		elementUtil.doSelectDropDownByIndex(checkoutPageMode, pageMode);
		jsUtil.clickByJS(checkOut);
		TimeUtil.shortWait();
		elementUtil.clickWhenReady(cardNumber, 10);
		elementUtil.doSendKeys(cardNumber, cno);
		elementUtil.doSendKeys(expiryDate, expiry);
		elementUtil.doSendKeys(cardCvv, cvv);
		elementUtil.doClick(payNow);
	}
	
	public void clickOn3Ds2Page() {
		elementUtil.waitForFrameAndSwitchToIt(submitFrame, Constants.WAIT_TIME);
		elementUtil.waitForFrameAndSwitchToIt(otpFrame, Constants.WAIT_TIME);
		elementUtil.doActionsSendKeys(otp, "1234");
		elementUtil.doActionsClick(submit3DS2);
	}
}
