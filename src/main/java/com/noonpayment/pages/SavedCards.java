package com.noonpayment.pages;

import com.noonpayment.util.Constants;
import com.noonpayment.util.ElementUtil;
import com.noonpayment.util.JavaScriptUtil;
import com.noonpayment.util.TimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SavedCards {

	private final By addToCart = By.xpath("//a[@href='/Shopping/ShoppingCart?id=1']");
	private final By checkOut = By.id("btnCheckout");
	private final By addCheckoutPageMode = By.id("ddlChkMode");
	private final By savedCard = By.id("rdbSavedCard_0");
	private final By savedCardCVV = By.id("txtSavedCardsFormCvv_0");
	private final By payNow = By.id("btnPayWithCard");
	private final By submitFrame = By.id("redirectTo3ds1Frame");
	private final By submit = By.xpath("//input[@value='Submit']");
	private final By paymentDetails = By.xpath(".//*[@id='page-wrapper']/div[2]/div/div/div[1]/div");

	private final ElementUtil elementUtil;
	private final JavaScriptUtil jsUtil;

	public SavedCards(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}

	public String getSavedCardHostedPageStatus() {
		elementUtil.clickWhenReady(addToCart,Constants.WAIT_TIME);
		jsUtil.clickByJS(checkOut);
		elementUtil.waitForElementPresent(savedCard, Constants.WAIT_TIME);
		jsUtil.clickByJS(savedCard);
		elementUtil.doSendKeys(savedCardCVV,"345");
		elementUtil.doClick(payNow);
		elementUtil.waitForFrameAndSwitchToIt(submitFrame, Constants.WAIT_TIME);
		elementUtil.clickWhenReady(submit,Constants.WAIT_TIME);
		TimeUtil.mediumWait();
		return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails,Constants.WAIT_TIME);
	}
	
	public String getSavedCardLightBoxPageStatus() {
		elementUtil.clickWhenReady(addToCart,Constants.WAIT_TIME);
		elementUtil.doSelectDropDownByIndex(addCheckoutPageMode,0);
		jsUtil.clickByJS(checkOut);
		elementUtil.waitForFrameAndSwitchToIt(1,Constants.WAIT_TIME);
		TimeUtil.shortWait();
		elementUtil.waitForElementPresent(savedCard, Constants.WAIT_TIME);
		jsUtil.clickByJS(savedCard);
		elementUtil.doSendKeys(savedCardCVV,"345");
		elementUtil.doClick(payNow);
		elementUtil.waitForFrameAndSwitchToIt(submitFrame, Constants.WAIT_TIME);
		elementUtil.clickWhenReady(submit,Constants.WAIT_TIME);
		TimeUtil.mediumWait();
		return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails,Constants.WAIT_TIME);
	}
	
	public String getSavedCardIframePageStatus() {
		elementUtil.clickWhenReady(addToCart,Constants.WAIT_TIME);
		elementUtil.doSelectDropDownByIndex(addCheckoutPageMode,2);
		jsUtil.clickByJS(checkOut);
		elementUtil.waitForFrameAndSwitchToIt(0,Constants.WAIT_TIME);
		TimeUtil.shortWait();
		elementUtil.waitForElementPresent(savedCard, Constants.WAIT_TIME);
		jsUtil.clickByJS(savedCard);
		TimeUtil.mediumWait();
		elementUtil.doSendKeys(savedCardCVV,"345");
		elementUtil.doClick(payNow);
		TimeUtil.mediumWait();
		elementUtil.waitForFrameAndSwitchToIt(submitFrame, Constants.WAIT_TIME);
		elementUtil.clickWhenReady(submit,Constants.WAIT_TIME);
		TimeUtil.mediumWait();
		return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails,Constants.WAIT_TIME);
	}
}