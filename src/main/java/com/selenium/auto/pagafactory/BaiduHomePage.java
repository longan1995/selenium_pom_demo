package com.selenium.auto.pagafactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class BaiduHomePage extends BasePage {

    private final int TIMEOUT = 10;    

    
	// 百度输入框
	@FindBy(id = "kw")
	// 意思：找到页面元素后就缓存元素,重复使用它，提高效率
	@CacheLookup
	// 文本输入框
	public WebElement search_input;

	// 百度一下按钮
	@FindBy(id = "su")
	@CacheLookup
	// 百度搜索按钮
	public WebElement search_btn;

	// 常量，测试页面地址
	// 在web自动化测试中，可以使用这个方法在每个po设计页面直接测试某个页面，而不需要从其他测试页面跳转过来
	private static final String TEST_PAGE_URL = "https://www.baidu.com";

	// 打开首页
	public void open() {
		driver.get(TEST_PAGE_URL);
		// 最大化浏览器
		driver.manage().window().maximize();
	}

	// 关闭浏览器
	public void close() {
		// 还原一个干净的浏览器环境
		driver.quit();
	}

	/**
	 * PageFactory是为了支持页面设计模式而开发出来的,它提供初始化页面元素的方法；
	 * 如果@FindBy没有指定方法，它会默认找id、然后name，如果找不到就报错，如果存在就会初始化这个元素。
	 */
	// 每个类都有一个默认的构造方法
	public BaiduHomePage() {
		// 当前的页面初始化页面元素
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);;
	}

	/**
	 * 操作浏览器的行为：输入内容并搜索，返回一个结果页面
	 * 
	 * @param content
	 * @return
	 */
	public SearchResultPage inputTextAndSearch(String content) {
		// 搜索内容
		search_input.sendKeys(content);
		// 点击搜索按钮
		search_btn.click();
		// 返回一个页面对象，在测试用例中可以用对象接收调用其中的方法
		return new SearchResultPage();
	}

}