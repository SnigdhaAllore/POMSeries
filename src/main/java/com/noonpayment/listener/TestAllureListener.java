package com.noonpayment.listener;

import com.noonpayment.base.TestBase;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestAllureListener implements ITestListener {
	private static final Logger LOGGER = LogManager.getLogger(TestAllureListener.class);
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {return message;}

	@Attachment(value = "{0}", type = "text/html")
	public static String attachHtml(String html) {
		return html;
	}
	@Override
	public void onStart(ITestContext iTestContext) {
		LOGGER.info("*************** I am in onStart " +iTestContext.getName()+" ***************");
	}
	@Override
	public void onFinish(ITestContext iTestContext) {
		LOGGER.info("*************** I am in onFinish " +iTestContext.getName()+" ***************");
	}
	@Override
	public void onTestStart(ITestResult iTestResult) {
		LOGGER.info("*************** I am in onTestStart " +getClassName(iTestResult)+" :: "+iTestResult.getName()+ " method start  ***************");
	}
	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		LOGGER.info("*************** I am in onTestSuccess " +getClassName(iTestResult)+" :: "+iTestResult.getName()+ " method succeed  ***************");
	}
	@Override
	public void onTestFailure(ITestResult iTestResult) {
        LOGGER.info("*************** I am in onTestFailure " +getClassName(iTestResult)+" :: "+iTestResult.getName()+ " method failed  ***************");
        WebDriver driver = TestBase.getDriver();
        saveScreenshotPNG(driver);
        saveTextLog(iTestResult.getName() + " failed and screenshot taken!");
	}
	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		LOGGER.info("*************** I am in onTestSkipped " +getClassName(iTestResult)+" :: "+iTestResult.getName()+ " method skipped  ***************");
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		LOGGER.info("*************** Test failed but it is in defined success ratio " +getClassName(iTestResult)+" :: "+iTestResult.getName()+"  method ***************");
	}
	private String getClassName(ITestResult iTestResult)	{
		return iTestResult.getTestClass().getRealClass().getSimpleName();
	}
}