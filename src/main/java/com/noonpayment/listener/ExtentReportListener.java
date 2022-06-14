package com.noonpayment.listener;

import com.noonpayment.base.TestBase;
import com.noonpayment.util.Constants;
import com.noonpayment.util.EmailAttachment;
import com.noonpayment.util.TimeUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.*;
import org.testng.xml.XmlSuite;
import java.util.List;
import java.util.Map;

public class ExtentReportListener implements IReporter {

	private ExtentReports extent;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String path) {

		extent = new ExtentReports(Constants.REPORT_LOCATION , true);
		extent.addSystemInfo("Host Name", "windows");
		extent.addSystemInfo("User Name", "Snigdha");
		extent.addSystemInfo("Environment", TestBase.ENV);
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();
			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();
				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			}
		}
		extent.flush();
		extent.close();
		EmailAttachment.sendEmail();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status) {
		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				ExtentTest test = extent.startTest(result.getMethod().getMethodName());
				test.setStartedTime( TimeUtil.getTime(result.getStartMillis()));
				test.setEndedTime(TimeUtil.getTime(result.getEndMillis()));
				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);
				if (result.getThrowable() != null) {
					test.log(status,result.getTestName(), result.getThrowable());
					if (result.getStatus() == ITestResult.FAILURE) {
						String encodedBase64 = "data:image/png;base64," + result.getAttribute(result.getName()).toString();
						test.log(LogStatus.FAIL, test.addBase64ScreenShot(encodedBase64));
					}
				} else {
					test.log(status, "Test Successfully " + status.toString().toLowerCase() + "ed");
				}
				extent.endTest(test);
			}
		}
	}
}