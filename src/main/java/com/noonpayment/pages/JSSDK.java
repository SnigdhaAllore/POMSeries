package com.noonpayment.pages;

import com.noonpayment.util.Constants;
import com.noonpayment.util.ElementUtil;
import com.noonpayment.util.JavaScriptUtil;
import com.noonpayment.util.TimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


public class JSSDK {

	private final By customPages = By.xpath("(//a[@href='#']/span[1])[3]");
	private final By customCheckOut = By.xpath("//a[@href='/Checkout']");
	private final By amount = By.id("Amount");
	private final By orderName = By.id("ProductName");
	private final By category = By.id("Category");
	private final By returnUrl = By.id("ReturnUrl");
	private final By checkOut = By.id("btnCheckout");
	private final By checkoutPageMode = By.id("CheckoutPageMode");
	private final By cardNumber = By.id("cardNumberInput");
	private final By expiryDate = By.id("expiryDate");
	private final By cardCvv = By.id("cvvInput");
	private final By payNow = By.id("pay-btn");
	private final By paymentDetails = By.xpath(".//*[@id='page-wrapper']/div[2]/div/div/div[1]/div");
	private final By submit = By.xpath("//input[@value='Submit']");
	private final By submitFrame = By.id("redirectTo3ds1Frame");
	//private final By otpFrame = By.xpath("//iframe[contains(@id, 'cardinal-stepUpIframe-')]");
	private final By jsCardFrame = By.id("__noonPaymentsCardNumberIframe");
	private final By jsDateFrame = By.id("__noonPaymentsExpiryDateIframe");
	private final By jsCvvFrame = By.id("__noonPaymentsCvvIframe");
	private final By submit3DS2 = By.xpath("//input[@value='SUBMIT']");
	private final By otp = By.name("challengeDataEntry");
	private final By EMIRadio = By.xpath("//label[normalize-space()='3 months']");
	private final By getInTouch = By.xpath("//button[contains(.,'Get in touch')]");

	private final ElementUtil elementUtil;
	private final JavaScriptUtil jsUtil;
	private final WebDriver driver;

	public JSSDK(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
		this.driver = driver;		
	}

	public String getJSSDKCheckOutStatus(String cno, String expiry, String cvv) {
		customCheckOutActions("10", Constants.CATEGORY_DATA);
		elementUtil.doSelectDropDownByIndex(checkoutPageMode, 3);
		jsUtil.clickByJS(checkOut);
		cardPaymentActions("5200000000000007", "12/34", "343");
		return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails, 10);
	}

	
	/*
	 * public boolean getJSSDKCheckOutCustomURLStatus(String cno, String expiry,
	 * String cvv) { customCheckOutActions();
	 * elementUtil.doSelectDropDownByIndex(checkoutPageMode, 3);
	 * elementUtil.doSendKeys(returnUrl, Constants.NOONPAYMENTS_URL);
	 * jsUtil.clickByJS(checkOut); cardPaymentActions("4000000000000002", "12/23",
	 * "123"); if (elementUtil.getPageUrl().contains(Constants.NOONPAYMENTS_URL)) {
	 * System.out.println("Return url matches"); } else {
	 * System.out.println("Return url does not match"); }; return
	 * elementUtil.checkWaitForElementPresent(getInTouch, 10); }
	 */
	 

	public String getJSSDKCheckOut3DStatus(String cno, String expiry, String cvv) {
		customCheckOutActions("20","secure");
		elementUtil.doSelectDropDownByIndex(checkoutPageMode, 3);
		jsUtil.clickByJS(checkOut);
		cardPaymentActions(cno, expiry, cvv);
		elementUtil.waitForFrameAndSwitchToIt(submitFrame, 10);
		elementUtil.clickWhenReady(submit, 10);
		TimeUtil.longWait();
		return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails, 10);

	}

	public String getJSSDKCheckOut3DCustomURLStatus(String cno, String expiry, String cvv) {
		customCheckOutActions("20","secure");
		elementUtil.doSelectDropDownByIndex(checkoutPageMode, 3);
		elementUtil.doSendKeys(returnUrl, Constants.NOONPAYMENTS_URL);
		jsUtil.clickByJS(checkOut);
		cardPaymentActions(cno, expiry, cvv);
		elementUtil.waitForFrameAndSwitchToIt(submitFrame, 10);
		elementUtil.clickWhenReady(submit, 10);
		TimeUtil.longWait();
		if (elementUtil.getPageUrl().contains(Constants.NOONPAYMENTS_URL))
		{

		System.out.println("Return url matches");
		}
		else {
			System.out.println("Return url does not match");
		};
		//return elementUtil.checkWaitForElementPresent(getInTouch,10);
		String temp = driver.getCurrentUrl();
		return temp;
		//return temp.contains("orderId=") && temp.contains("paymentType=Card");
	}

	public String getJSSDKCheckOut3D2Status(String cno, String expiry, String cvv) {
		customCheckOutActions("150","restapi");
		elementUtil.doSelectDropDownByIndex(checkoutPageMode, 3);
		jsUtil.clickByJS(checkOut);
		cardPaymentActions("4456530000001096", "12/23", "525");
		TimeUtil.longWait();
		//elementUtil.waitForFrameAndSwitchToIt(otpFrame, 10);
		//elementUtil.waitForFrameAndSwitchToIt(submitFrame, 10);
		elementUtil.waitForFrameAndSwitchToIt(0,10);
		elementUtil.waitForFrameAndSwitchToIt(0,10);
		elementUtil.doActionsSendKeys(otp, "1234");
		elementUtil.doActionsClick(submit3DS2);
		return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails, 10);

	}

	public String getJSSDKFailedStatus(String cno, String expiry, String cvv) {
		customCheckOutActions("4051",Constants.CATEGORY_DATA);	
		elementUtil.doSelectDropDownByIndex(checkoutPageMode, 3);
		jsUtil.clickByJS(checkOut);
		cardPaymentActions(cno, expiry, cvv);
		return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails, 10);

	}
	public String getJSSDKEMIStatus(String cno, String expiry, String cvv) {
		customCheckOutActions("100",Constants.CATEGORY_DATA);
		elementUtil.doSelectDropDownByIndex(checkoutPageMode, 3);
		elementUtil.doSendKeys(returnUrl, Constants.NOONPAYMENTS_URL);
		jsUtil.clickByJS(checkOut);
		cardPaymentActions(cno, expiry, cvv);
		return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails, 10);

	}
	


	public void customCheckOutActions(String jsAmount, String jsCategory) {
		jsUtil.waitAndClickByJS(customPages, 10);
		jsUtil.waitAndClickByJS(customCheckOut, 10);
		elementUtil.doSendKeys(amount, jsAmount);
		elementUtil.doSendKeys(orderName, "test");
		elementUtil.doSendKeys(category, jsCategory);
		elementUtil.doKeysReturn(category, Keys.RETURN);
	}

	public void cardPaymentActions(String cno, String expiry, String cvv) {
		TimeUtil.longWait();
		elementUtil.waitForFrameAndSwitchToIt(jsCardFrame, 10);
		elementUtil.doSendKeys(cardNumber, cno.trim());
		elementUtil.defaultcontent();
		elementUtil.waitForFrameAndSwitchToIt(jsDateFrame, 10);
		jsUtil.doJSSendKeys(expiryDate, expiry.trim());
		elementUtil.defaultcontent();
		elementUtil.waitForFrameAndSwitchToIt(jsCvvFrame, 10);
		jsUtil.doJSSendKeys(cardCvv, cvv.trim());
		elementUtil.defaultcontent();
		jsUtil.clickByJS(payNow);
		TimeUtil.longWait();
	}
	
	public void cardPaymentActionsEMI(String cno, String expiry, String cvv) {
		TimeUtil.longWait();
		elementUtil.waitForFrameAndSwitchToIt(jsCardFrame, 10);
		elementUtil.doSendKeys(cardNumber, cno.trim());
		elementUtil.defaultcontent();
		elementUtil.waitForFrameAndSwitchToIt(jsDateFrame, 10);
		jsUtil.doJSSendKeys(expiryDate, expiry.trim());
		elementUtil.defaultcontent();
		elementUtil.waitForFrameAndSwitchToIt(jsCvvFrame, 10);
		jsUtil.doJSSendKeys(cardCvv, cvv.trim());
		elementUtil.defaultcontent();
		jsUtil.clickByJS(EMIRadio);
		elementUtil.defaultcontent();
		jsUtil.clickByJS(payNow);
		TimeUtil.longWait();
	}
	

}