package com.joe.test.po.demo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.auto.po.HomePage;
import com.selenium.auto.po.SearchResultPage;
import com.selenium.auto.po.base.BrowserDriver;

/**
 * 
 * TODO:百度po测试用例演练，@DataProvider注解，参数话多次查询结果
 *
 * @author Joe-Tester
 * @time 2021年3月9日
 * @file test_baidu_search.java
 */
public class test_dataprovider {

	WebDriver driver;
	HomePage homePage;

	@BeforeMethod
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")
						+ "/src/test/resources/chromedriver.exe");
		BrowserDriver browserDriver = new BrowserDriver();
		driver = browserDriver.getBrowser();
		driver.get("https://www.baidu.com");
		homePage = new HomePage(driver);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(2000);
		driver.quit();
	}

	@DataProvider(name = "testData")
	public Object[][] testData() {
		return new Object[][] { { "selenium" }, { "appium" } };
	}

	@Test(dataProvider = "testData")
	public void test_baidu_demo(String content) {

		SearchResultPage result = homePage.search_input(content);
		result.checkSearchResult();
		result.goToResultDetails();
	}

}