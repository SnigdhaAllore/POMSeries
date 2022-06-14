package com.noonpayment.base;

import com.noonpayment.listener.TestAllureListener;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.time.Duration;

@Listeners(TestAllureListener.class)
public class TestBase {
	private static final Logger LOGGER = LogManager.getLogger(TestBase.class);
	public static WebDriver driver;
	public static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	public static Environment environment;
	public static String ENV;
	//test
	@Parameters({ "environment" })
	@BeforeTest
	public static void setUp(String env) {
		ConfigFactory.setProperty("environment", env);
		environment = ConfigFactory.create(Environment.class);
		ENV=env; 
		//BuildNumber = environment.getBuildNo(); 
		driver=startBrowserInitialization();
	}

	@AfterTest
	public void driverQuit() {
		driver.quit();
	}

	@BeforeMethod
	public void navigateToURL()	{
		if(!getDriver().getCurrentUrl().equalsIgnoreCase(environment.getCheckOutProURL().trim())) {
			getDriver().navigate().to(environment.getCheckOutProURL().trim());
			doAcceptAlertIfPresent();
		}
	}

	@AfterMethod
	public void takeSnapShot(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			TakesScreenshot ts = (TakesScreenshot) getDriver();
			String base64Image=ts.getScreenshotAs(OutputType.BASE64);
			result.setAttribute(result.getName(),base64Image);
		}
		getDriver().manage().deleteAllCookies();
	}

	public static WebDriver startBrowserInitialization() {
		switch (environment.getBrowser()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				//WebDriverManager.chromedriver().setup();
				if("Y".equalsIgnoreCase(environment.checkHeadless()))
					tlDriver.set(new ChromeDriver(getChromeOptions()));
				else
					tlDriver.set(new ChromeDriver());
				break;
			case "ff":
				WebDriverManager.firefoxdriver().setup();
				tlDriver.set("true".equalsIgnoreCase(environment.checkHeadless())
						? new FirefoxDriver(new FirefoxOptions().addArguments("--headless"))
						: new FirefoxDriver());
				break;
			case "ie":
				WebDriverManager.iedriver().setup();
				tlDriver.set(new InternetExplorerDriver());
				break;
			case "edge":
				
				 WebDriverManager.edgedriver().setup(); 
				 tlDriver.set(new EdgeDriver());
				 
				
				/*
				 * System.setProperty("webdriver.edge.driver","C://Softwares//msedgedriver.exe")
				 * ; tlDriver.set(new EdgeDriver());
				 */
				 

				break;	
			default:
				tlDriver.set(new ChromeDriver());
				break;
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(environment.getCheckOutProURL().trim());
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	private static ChromeOptions getChromeOptions() {
		final ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--start-maximized");
		return options;
	}

	private void doAcceptAlertIfPresent(){
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
			wait.until(ExpectedConditions.alertIsPresent()).accept();
		} catch (TimeoutException e) {
			LOGGER.info("");
		}
	}
}