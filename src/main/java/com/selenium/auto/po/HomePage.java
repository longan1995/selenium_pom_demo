package com.selenium.auto.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 这里提取每个页面的元素<属性> 封装元素操作行为抽象出来的页面对象类
 * 
 * @author Joe-Tester
 *
 */
public class HomePage extends BasePage {

	/**
	 * 构造函数用来接收浏览器驱动；继承了BasePage
	 * 
	 * @param driver
	 */
	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// 百度输入框
	By search_input = By.id("kw");
	// 百度一下按钮
	By search_btn = By.id("su");

	// 测试地址，可以在不同页面对象类中直接使用，可以跳过中间操作步骤
	private static final String TEST_PAGE_URL = "https://www.baidu.com";

	/**
	 * 打开测试地址
	 */
	public void open() {
		openUrl(TEST_PAGE_URL);
	}

	/**
	 * 关闭所有浏览器
	 */
	public void quitBrowser() {
		closeBrowser();
	}

	/**
	 * 这里需要返回一个操作行为的结果页
	 * 
	 * @param content
	 */
	public SearchResultPage search_input(String content) {
		// 输入搜索内容
		getElement(search_input).sendKeys(content);
		// 点击搜索
		getElement(search_btn).click();
		// 返回一个结果页
		return new SearchResultPage(driver);
	}

}