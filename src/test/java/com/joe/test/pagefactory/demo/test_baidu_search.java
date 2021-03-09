package com.joe.test.pagefactory.demo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.auto.pagafactory.BaiduHomePage;
import com.selenium.auto.pagafactory.SearchResultPage;

/**
 * 
 * 这是一个po设计模式下的测试用例类
 * 
 * @author Joe-Tester
 *
 */
public class test_baidu_search {

	public BaiduHomePage homePage;

	/**
	 * 每个用例类测试之前只执行一次
	 */
	@BeforeClass
	public void bf() {
		homePage = new BaiduHomePage();
		homePage.open();
	}

	/**
	 * 测试用例：根据测试用例的数据再进行优化；所有在代码中的测试数据都属于硬编码
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void test_baidu_demo() throws InterruptedException {
		SearchResultPage checkResult = homePage.inputTextAndSearch("selenium");
		checkResult.checkSearchResult();
		checkResult.goToResultDetails();
	}

	/**
	 * 每个用例类测试之后只执行一次
	 * 
	 * @throws InterruptedException
	 */
	@AfterClass
	public void af() throws InterruptedException {
		Thread.sleep(2000);
		homePage.close();
	}
}
