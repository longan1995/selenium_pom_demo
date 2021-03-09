package com.joe.test.po.demo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.auto.po.HomePage;
import com.selenium.auto.po.SearchResultPage;
import com.selenium.auto.po.base.BrowserDriver;

/**
 * 
 * TODO:测试类
 *
 * @author Joe-Tester
 * @time 2021年3月9日
 * @file test_baidu_search.java
 */
public class test_baidu_search {

	WebDriver driver;

	@BeforeClass
	public void bf() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")
						+ "/src/test/resources/chromedriver.exe");
		BrowserDriver browserDriver = new BrowserDriver();
		driver=browserDriver.getBrowser();
		driver.get("https://www.baidu.com");

	}

	@AfterClass
	public void af() throws Exception {
		Thread.sleep(2000);
		driver.quit();
	}

	@Test
	public void test_baidu_demo() {
		HomePage homePage=new HomePage(driver);
		SearchResultPage result=homePage.search_input("selenium");
		result.checkSearchResult();
		result.goToResultDetails();
	}

}