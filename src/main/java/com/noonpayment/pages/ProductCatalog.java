package com.noonpayment.pages;

import com.noonpayment.util.Constants;
import com.noonpayment.util.ElementUtil;
import com.noonpayment.util.JavaScriptUtil;
import com.noonpayment.util.TimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class ProductCatalog {

    private final By customPages = By.xpath("(//a[@href='#']/span[1])[3]");
    private final By customCheckOut = By.xpath("//a[@href='/Checkout']");
    private final By addToCart = By.xpath("//a[@href='/Shopping/ShoppingCart?id=1']");
    private final By checkOut = By.id("btnCheckout");
    private final By amount = By.id("Amount");
    private final By orderName = By.id("ProductName");
    private final By category = By.id("Category");
    private final By checkoutPageMode = By.id("CheckoutPageMode");
    private final By returnUrl = By.id("ReturnUrl");
    private final By existingToken = By.id("ExistingToken");
    private final By paymentToken = By.id("ExistingPaymentToken");
    private final By savedCard = By.id("rdbSavedCard_0");
    private final By savedCardCVV = By.id("txtSavedCardsFormCvv_0");
    private final By useANewCard = By.xpath("//label[contains(.,'Use a new card')]");
    private final By cardNumber = By.id("txtNewCardFormCardNumber");
    private final By expiryDate = By.id("txtNewCardFormExpiryDate");
    private final By cardCvv = By.id("txtNewCardFormCvv");
    private final By payNow = By.id("btnPayWithCard");
    private final By submitFrame = By.id("redirectTo3ds1Frame");
    private final By submit = By.xpath("//input[@value='Submit']");
    private final By paymentDetails = By.xpath(".//*[@id='page-wrapper']/div[2]/div/div/div[1]/div");
    private final By getInTouch = By.xpath("//button[contains(text(),'Get in touch')]");

    private final ElementUtil elementUtil;
    private final JavaScriptUtil jsUtil;

    public ProductCatalog(WebDriver driver) {
        elementUtil = new ElementUtil(driver);
        jsUtil = new JavaScriptUtil(driver);
    }
    public String getProductCatalogCheckoutStatus(String cno, String expiry, String cvv) {
        jsUtil.clickByJS(addToCart);
        jsUtil.clickByJS(checkOut);
        elementUtil.clickWhenReady(useANewCard,Constants.WAIT_TIME);
        cardPaymentActions(cno,expiry,cvv);
        elementUtil.waitForFrameAndSwitchToIt(submitFrame, Constants.WAIT_TIME);
        elementUtil.clickWhenReady(submit,Constants.WAIT_TIME);
        return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails,Constants.WAIT_TIME);
    }
    public String getHostedPageCheckoutStatus(String cno, String expiry, String cvv) {
        customCheckOutActions();
        elementUtil.doSelectDropDownByIndex(checkoutPageMode,1);
        jsUtil.clickByJS(checkOut);
        cardPaymentActions(cno,expiry,cvv);
        return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails,Constants.WAIT_TIME);
    }
    public String getLightBoxCheckoutStatus(String cno, String expiry, String cvv) {
        customCheckOutActions();
        elementUtil.doSelectDropDownByIndex(checkoutPageMode,0);
        jsUtil.clickByJS(checkOut);
        elementUtil.waitForFrameAndSwitchToIt(1,Constants.WAIT_TIME);
        cardPaymentActions(cno,expiry,cvv);
        return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails,Constants.WAIT_TIME);
    }
    public String getFrameCheckoutStatus(String cno, String expiry, String cvv) {
        customCheckOutActions();
        elementUtil.doSelectDropDownByIndex(checkoutPageMode,2);
        jsUtil.clickByJS(checkOut);
        elementUtil.waitForFrameAndSwitchToIt(0,Constants.WAIT_TIME);
        cardPaymentActions(cno,expiry,cvv);
        return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails,Constants.WAIT_TIME);
    }

    public boolean getReturnURLPageTitle(String cno, String expiry, String cvv) {
        customCheckOutActions();
        elementUtil.doSelectDropDownByIndex(checkoutPageMode,1);
        elementUtil.doSendKeys(returnUrl, Constants.NOONPAYMENTS_URL);
        jsUtil.clickByJS(checkOut);
        cardPaymentActions(cno,expiry,cvv);
        return elementUtil.checkWaitForElementPresent(getInTouch,Constants.WAIT_TIME);
    }

    public String getSavedTokenStatus(String tokenId) {
        customCheckOutActions();
        elementUtil.doSelectDropDownByIndex(checkoutPageMode,1);
        jsUtil.clickByJS(existingToken);
        elementUtil.doSendKeys(paymentToken,tokenId);
        jsUtil.clickByJS(checkOut);
        elementUtil.waitForElementPresent(savedCard, Constants.WAIT_TIME);
        jsUtil.clickByJS(savedCard);
        elementUtil.doSendKeys(savedCardCVV,"345");
        elementUtil.doClick(payNow);
        return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails,Constants.WAIT_TIME);
    }

    public void customCheckOutActions() {
        jsUtil.waitAndClickByJS(customPages,Constants.WAIT_TIME);
        jsUtil.waitAndClickByJS(customCheckOut,Constants.WAIT_TIME);
        elementUtil.doSendKeys(amount,"75");
        elementUtil.doSendKeys(orderName,"test");
        elementUtil.doSendKeys(category,"default");
        elementUtil.doKeysReturn(category,Keys.RETURN);
    }
    public void cardPaymentActions(String cno, String expiry, String cvv) {
        TimeUtil.shortWait();
        jsUtil.waitAndClickByJS(cardNumber,Constants.WAIT_TIME);
        elementUtil.doSendKeys(cardNumber,cno);
        elementUtil.doSendKeys(expiryDate,expiry);
        elementUtil.doSendKeys(cardCvv,cvv);
        elementUtil.doKeysReturn(cardCvv,Keys.RETURN);
        jsUtil.waitAndClickByJS(payNow,Constants.WAIT_TIME);
    }
}