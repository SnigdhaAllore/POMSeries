package com.noonpayment.pages;

import com.noonpayment.util.Constants;
import com.noonpayment.util.ElementUtil;
import com.noonpayment.util.JavaScriptUtil;
import com.noonpayment.util.TimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPageInArabic {
	private final By customPages = By.xpath("(//a[@href='#']/span[1])[3]");
	private final By customCheckOut = By.xpath("//a[@href='/Checkout']");
	private final By checkOut = By.id("btnCheckout");
	private final By amount = By.id("Amount");
	private final By orderName = By.id("ProductName");
	private final By category = By.id("Category");
	private final By checkoutPageMode = By.id("CheckoutPageMode");
	private final By language = By.id("CheckoutLanguage");
	private final By payNowButtonInArabic = By.xpath("//span[contains(text(),' ادفع الآن ')]");

	private final ElementUtil elementUtil;
	private final JavaScriptUtil jsUtil;

	public CheckOutPageInArabic(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	public String getArabicInLightBoxCheckoutStatus() {
		jsUtil.waitAndClickByJS(customPages,Constants.WAIT_TIME);
		jsUtil.waitAndClickByJS(customCheckOut,Constants.WAIT_TIME);
		elementUtil.doSendKeys(amount,"75");
		elementUtil.doSendKeys(orderName,"test");
		elementUtil.doSendKeys(category,"demo_aed");
		elementUtil.doSelectDropDownByIndex(checkoutPageMode, 0);
		elementUtil.doSelectDropDownByVisibleText(language,"Arabic");
		jsUtil.waitAndClickByJS(checkOut,Constants.WAIT_TIME);
		elementUtil.waitForFrameAndSwitchToIt(1,Constants.WAIT_TIME);
		TimeUtil.mediumWait();
		return elementUtil.waitForElementPresentAndGetText(payNowButtonInArabic,Constants.WAIT_TIME);
	}
	public String getArabicInhostedPageCheckoutStatus() {
		jsUtil.waitAndClickByJS(customPages,Constants.WAIT_TIME);
		jsUtil.waitAndClickByJS(customCheckOut,Constants.WAIT_TIME);
		elementUtil.doSendKeys(amount,"75");
		elementUtil.doSendKeys(orderName,"test");
		elementUtil.doSendKeys(category,"demo_aed");
		elementUtil.doSelectDropDownByIndex(checkoutPageMode, 1);
		elementUtil.doSelectDropDownByVisibleText(language,"Arabic");
		jsUtil.waitAndClickByJS(checkOut,Constants.WAIT_TIME);
		return elementUtil.waitForElementPresentAndGetText(payNowButtonInArabic,Constants.WAIT_TIME);
	}
}