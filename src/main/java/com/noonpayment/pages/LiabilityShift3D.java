package com.noonpayment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.noonpayment.util.Constants;
import com.noonpayment.util.ElementUtil;
import com.noonpayment.util.TimeUtil;

public class LiabilityShift3D  {
	
	private final By submit = By.xpath("//input[@value='Submit']");
	private final By paymentDetails = By.xpath(".//*[@id='page-wrapper']/div[2]/div/div/div[1]/div");
	private final By activateNow = By.xpath("//input[@value='Activate Now']");
	private final By exit = By.xpath("//a[contains(text(),'Exit')]");
	private final By continueBtn = By.xpath(" //input[@name='Submit']");

	private final ElementUtil elementUtil;
	private final  FullySecure3D fullySecure3D;

	public LiabilityShift3D(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		fullySecure3D = new FullySecure3D(driver);
	}
	public String getHostedLiabilityShift3DPageCheckoutByType(String cno, String expiry, String cvv, String type) {
		fullySecure3D.customCheckoutHostedFlow(cno, expiry, cvv,1);
		if("TypeA".equalsIgnoreCase(type))
			elementUtil.clickWhenReady(submit,Constants.WAIT_TIME);
		else if("TypeB".equalsIgnoreCase(type) )
			elementUtil.clickWhenReady(activateNow,Constants.WAIT_TIME);
		else if("TypeC".equalsIgnoreCase(type) ){
			elementUtil.clickWhenReady(exit,Constants.WAIT_TIME);
		 	elementUtil.acceptAlert(Constants.WAIT_TIME);
		}
		else if("TypeD".equalsIgnoreCase(type) ) 
			elementUtil.clickWhenReady(continueBtn,Constants.WAIT_TIME);
		TimeUtil.shortWait();
		return elementUtil.waitForElementPresentAndGetStatusText(paymentDetails,Constants.WAIT_TIME);
	}
}