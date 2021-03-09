package com.selenium.auto.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 每个页面的元素及操作行为抽象出来的页面对象类
 * 
 * @author Joe-Tester
 *
 */
public class HomePage extends BasePage {

	/**
	 * 构造函数用来接收浏览器驱动
	 * 
	 * @param driver
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

	public void search_input(String content) {
		getElement(search_input).sendKeys(content);
		driver.findElement(search_btn).click();
	}

}