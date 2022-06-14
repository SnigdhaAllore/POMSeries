package com.noonpayment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.noonpayment.util.Constants;
import com.noonpayment.util.ElementUtil;
import com.noonpayment.util.JavaScriptUtil;
import com.noonpayment.util.TimeUtil;

public class Checkout3DScenarios {
	private final By customPages = By.xpath("(//a[@href='#']/span[1])[3]");
	private final By customCheckOut = By.xpath("//a[@href='/Checkout']");
	private final By checkOut = By.id("btnCheckout");
	private final By amount = By.id("Amount");
	private final By orderName = By.id("ProductName");
	private final By category = By.id("Category");
	private final By checkoutPageMode = By.id("CheckoutPageMode");
	private final By submitFrame = By.id("redirectTo3ds1Frame");
	private final By submit = By.xpath("//input[@value='Submit']");
	private final By paymentDetails = By.xpath(".//*[@id='page-wrapper']/div[2]/div/div/div[1]/div");

	private final ElementUtil elementUtil;
	private final JavaScriptUtil jsUtil;
	private final FullySecure3D fullySecure3D;
	private final  ProductCatalog productcatalog;
	


	public Checkout3DScenarios(WebDriver driver) {
		  elementUtil = new ElementUtil(driver);
		  jsUtil = new JavaScriptUtil(driver);
		  fullySecure3D = new FullySecure3D(driver);
		  productcatalog = new ProductCatalog(driver);
	}

	public String getHosted3DPageCheckoutStatus(String cno, String expiry, String cvv) { 
	  fullySecure3D.customCheckoutHostedFlow(cno, expiry, cvv,1);
	  elementUtil.clickWhenReady(submit,Constants.WAIT_TIME); 
	  TimeUtil.mediumWait(); 
	  return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails,Constants.WAIT_TIME); 
	  }
	 
	public String getIFrame3DPageCheckoutStatus(String cno, String expiry, String cvv) {
		jsUtil.waitAndClickByJS(customPages,Constants.WAIT_TIME);
		jsUtil.waitAndClickByJS(customCheckOut,Constants.WAIT_TIME);
		elementUtil.doSendKeys(amount,"75");
		elementUtil.doSendKeys(orderName,"test");
		elementUtil.doSendKeys(category,"secure");
        elementUtil.doSelectDropDownByIndex(checkoutPageMode,2);
        jsUtil.clickByJS(checkOut);
        elementUtil.waitForFrameAndSwitchToIt(0,Constants.WAIT_TIME);
        productcatalog.cardPaymentActions(cno,expiry,cvv);
        elementUtil.waitForFrameAndSwitchToIt(submitFrame, Constants.WAIT_TIME);
        elementUtil.clickWhenReady(submit,Constants.WAIT_TIME);
        TimeUtil.mediumWait();
		return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails,Constants.WAIT_TIME);
	}
}

