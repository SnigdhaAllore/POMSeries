package com.noonpayment.pages;

import com.noonpayment.util.Constants;
import com.noonpayment.util.ElementUtil;
import com.noonpayment.util.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Offers {
	private final By customPages = By.xpath("(//a[@href='#']/span[1])[3]");
	private final By customCheckOut = By.xpath("//a[@href='/Checkout']");
	private final By checkOut = By.id("btnCheckout");
	private final By amount = By.id("Amount");
	private final By orderName = By.id("ProductName");
	private final By category = By.id("Category");
	private final By checkoutPageMode = By.id("CheckoutPageMode");
	private final By cardNumber = By.id("txtNewCardFormCardNumber");
	private final By expiryDate = By.id("txtNewCardFormExpiryDate");
	private final By cardCvv = By.id("txtNewCardFormCvv");
	private final By payNow = By.id("btnPayWithCard");
	private final By exemptedAmount = By.id("OfferExcemptedAmount");
	private final By totalPayableDropdown = By.xpath("//div[@id='paymentCheckoutContainer']/np-header/div/div[2]/div[3]/span");
	private final By paymentDetails = By.xpath(".//*[@id='page-wrapper']/div[2]/div/div/div[1]/div");
	private final By totalPayableValue= By.xpath("(//div[@id='totalPaybleItems'])[4]");

	private final ElementUtil elementUtil;
	private final JavaScriptUtil jsUtil;

	public Offers(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	public String getOffersStatus(String cno, String expiry, String cvv) {
		jsUtil.waitAndClickByJS(customPages,Constants.WAIT_TIME);
		jsUtil.waitAndClickByJS(customCheckOut,Constants.WAIT_TIME);
		elementUtil.doSendKeys(amount,"100");
		elementUtil.doSendKeys(orderName,"test");
		elementUtil.doSendKeys(category,"brand");
		elementUtil.doSelectDropDownByIndex(checkoutPageMode,1);
		jsUtil.clickByJS(checkOut);
		elementUtil.clickWhenReady(cardNumber,Constants.WAIT_TIME);
		elementUtil.doSendKeys(cardNumber,cno);
		elementUtil.doSendKeys(expiryDate,expiry);
		elementUtil.doSendKeys(cardCvv,cvv);
		elementUtil.doClick(payNow);
		return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails,Constants.WAIT_TIME);
	}
	
	public String getOfferExemptedAmountText(String cno, String expiry, String cvv) {
		jsUtil.waitAndClickByJS(customPages,Constants.WAIT_TIME);
		jsUtil.waitAndClickByJS(customCheckOut,Constants.WAIT_TIME);
		elementUtil.doSendKeys(amount,"100");
		elementUtil.doSendKeys(orderName,"test");
		elementUtil.doSendKeys(category,"brand");
		elementUtil.doSendKeys(exemptedAmount,"10.00");
		elementUtil.doSelectDropDownByIndex(checkoutPageMode,1);
		jsUtil.waitAndClickByJS(checkOut,Constants.WAIT_TIME);
		elementUtil.clickWhenReady(cardNumber,Constants.WAIT_TIME);
		elementUtil.doSendKeys(cardNumber,cno);
		elementUtil.doSendKeys(expiryDate,expiry);
		elementUtil.doSendKeys(cardCvv,cvv);
		elementUtil.clickWhenReady(totalPayableDropdown,Constants.WAIT_TIME);
		return elementUtil.waitForElementPresentAndGetText(totalPayableValue,Constants.WAIT_TIME);
	}
}