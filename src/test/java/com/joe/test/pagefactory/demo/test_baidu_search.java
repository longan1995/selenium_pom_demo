package com.joe.test.pagefactory.demo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.auto.pagafactory.BaiduHomePage;
import com.selenium.auto.pagafactory.SearchResultPage;

/**
 * 
 * TODO:这是一个po设计模式下的测试用例类
 *
 * @author Joe-Tester
 * @time 2021年3月9日
 * @file test_baidu_search.java
 */
public class test_baidu_search {

	// 初始化页面对象，如果是测试用例通用，就在此处声明
	public BaiduHomePage homePage;

	/**
	 * 需要对@Before注解理解
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
