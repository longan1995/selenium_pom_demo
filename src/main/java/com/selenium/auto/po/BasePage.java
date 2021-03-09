package com.selenium.auto.po;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * TODO:页面基类，用来封装浏览器基础操作
 *
 * @author Joe-Tester
 * @time 2021年3月9日
 * @file BasePage.java
 */
public class BasePage {

	// 这是一个全局的driver，其他页面对象会继承该类的所有属性和方法
	protected WebDriver driver;

	/*
	 * 构造方法：super()和this()类似,区别是，super()从子类中调用父类的构造方法，this()在同一类内调用其它方法
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * 如果不声明上面带driver的构造函数，必须每次调用都需要传一个driver实例
	 * 
	 * @param locator
	 * @return
	 */
	public WebElement getOneElement(By locator) {
		List<WebElement> elementsList = getElementsList(locator);
		int size = elementsList.size();
		if (size == 1) {
			System.out.println("方法成功找到一个元素:" + locator);
			return elementsList.get(0);
		} else if (size > 1) {
			System.out.println("元素定位匹配多个!");
			return null;
		} else {
			return null;
		}
	}

	/**
	 * 获取页面元素 如果不声明上面带driver的构造函数，必须每次调用都需要传一个driver实例
	 * 
	 * @param locator
	 * @return
	 */
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element = this.driver.findElement(locator);
		} catch (NoSuchElementException e) {
			System.out.println("元素不存在！");
		}
		return element;
	}

	/**
	 * 获取元素集合
	 * 
	 * @param locator
	 * @return
	 */
	public List<WebElement> getElementsList(By locator) {
		List<WebElement> element = new ArrayList<WebElement>();
		try {
			element = this.driver.findElements(locator);
		} catch (NoSuchElementException e) {
			System.out.println("定位方法不支持!");
		}
		if (element.isEmpty()) {
			System.out.println("定位方法不支持,或<" + locator + ">元素未找到!");
		} else {
			System.out.println(element + "元素找到了!");
		}
		return element;
	}

	/**
	 * selenium隐式等待时间，解决时间同步问题，调用的是driver的实例，针对的是driver操作的所有元素都是隐式等待
	 * 
	 * @param timeout
	 */
	public void impWaitTime(int timeout) {
		this.driver.manage().timeouts()
				.implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	/**
	 * 显示等待是针对单个元素操作的等待
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement expWaitForElement(By locator, int timeout) {
		WebElement element = null;
		try {
			System.out.println(timeout + "秒之后出现");
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			element = wait.until(ExpectedConditions
					.visibilityOfElementLocated(locator));
			System.out.println("元素出现了");
		} catch (Exception e) {
			System.out.println("元素不存在");
			e.printStackTrace();
		}
		return element;
	}

	/**
	 * 查找所有元素，如果集合为空，则一个元素未找到
	 * 
	 * @param locator
	 * @return
	 */
	public boolean verifyElementIsPresent(By locator) {
		List<WebElement> els = getElementsList(locator);
		if (!els.isEmpty()) {
			System.out.println("页面元素存在");
			return true;
		} else {
			System.out.println("页面元素不存在");
			return false;
		}

	}

	/**
	 * 显示等待是针对单个元素操作的等待
	 * 
	 * @param locator
	 * @param timeout
	 */
	public void elementClickWhenReady(By locator, int timeout) {

		try {
			WebElement element = null;
			System.out.println(timeout + "秒之后出现");
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			element = wait.until(ExpectedConditions
					.elementToBeClickable(locator));
			element.click();
			System.out.println("元素出现了");
		} catch (Exception e) {
			System.out.println("元素不存在");
			e.printStackTrace();
		}
	}

	/**
	 * 退出浏览器
	 */
	public void closeBrowser() {
		driver.quit();
	}

	/**
	 * 打开浏览器
	 * 
	 * @param url
	 */
	public void openUrl(String url) {
		driver.get(url);
	}

}