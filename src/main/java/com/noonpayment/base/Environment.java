package com.noonpayment.base;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
	"classpath:${environment}.properties"
})
public interface Environment extends Config {

    @Key("noonpayment.browser.type")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("noonpayment.headless.value")
    @DefaultValue("Y")
    String checkHeadless();

    @Key("noonpayment.token.value")
    String getToken();

    @Key("noonpayment.epwd.value")
    String getEPwd();

    @Key("noonpayment.buildno.value")
    String getBuildNo();

    @Key("noonpayment.checkoutpro.url")
    String getCheckOutProURL();


    @Key("noonpayment.merchant.url")
    String getMerchantURL();

    @Key("noonpayment.merchant.username")
    String getMerchantUserName();

    @Key("noonpayment.merchant.password")
    String getMerchantPassword();

    @Key("noonpayment.merchantpro.url")
    String getMerchantProURL();

    @Key("noonpayment.merchantpro.username")
    String getMerchantProUserName();

    @Key("noonpayment.merchantpro.password")
    String getMerchantProPassword();
}