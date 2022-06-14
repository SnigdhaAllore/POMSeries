package com.noonpayment.pages;

import com.noonpayment.util.Constants;
import com.noonpayment.util.ElementUtil;
import com.noonpayment.util.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OmanNet {
	private final By addTocart = By.xpath("//a[@href='/Shopping/ShoppingCart?id=5']");
	private final By checkOut = By.id("btnCheckout");
	private final By cardNumber = By.id("txtNewCardFormCardNumber");
	private final By expiryDate = By.id("txtNewCardFormExpiryDate");
	private final By cardCvv = By.id("txtNewCardFormCvv");
	private final By cardHolderName = By.id("txtNewCardFormCardHolderName");
	private final By payNow = By.id("btnPayWithCard");
	private final By paymentDetails = By.xpath(".//*[@id='page-wrapper']/div[2]/div/div/div[1]/div");

	private final ElementUtil elementUtil;
	private final JavaScriptUtil jsUtil;

	public OmanNet(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}

	public String getOmanNetCheckoutStatus(String cno, String expiry, String cvv) {
		elementUtil.clickWhenReady(addTocart, Constants.WAIT_TIME);
		jsUtil.clickByJS(checkOut);
		elementUtil.clickWhenReady(cardNumber, Constants.WAIT_TIME);
		elementUtil.doSendKeys(cardNumber, cno);
		elementUtil.doSendKeys(expiryDate, expiry);
		elementUtil.doSendKeys(cardCvv, cvv);
		elementUtil.doSendKeys(cardHolderName, "Test User");
		elementUtil.doClick(payNow);
		return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails, Constants.WAIT_TIME);
	}
}