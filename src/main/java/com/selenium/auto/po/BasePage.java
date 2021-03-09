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
 * 页面基类，用来封装浏览器基础操作
 * 
 * @author Joe-Tester
 *
 */
public class BasePage {

	protected WebDriver driver;

	/*
	 * 构造方法
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * 如果不声明上面带driver的构造函数，必须每次调用都需要传一个driver实例
	 * 
	 * @param locator
	 * @param type
	 * @return
	 */
	public WebElement getOneElement(String locator, String type) {
		List<WebElement> elementsList = getElementsList(locator, type);
		int size = elementsList.size();
		if (size == 1) {
			System.out.println(type + "方法成功找到一个元素:" + locator);
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
	 * @param type
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
	 * @param type
	 * @return
	 */
	public List<WebElement> getElementsList(String locator, String type) {
		type = type.toLowerCase();
		List<WebElement> element = new ArrayList<WebElement>();
		try {
			element = this.driver.findElements(By.name(locator));
		} catch (NoSuchElementException e) {
			System.out.println(type + "定位方法不支持!");
		}
		if (element.isEmpty()) {
			System.out.println(type + "定位方法不支持,或<" + locator + ">元素未找到!");
		} else {
			System.out.println(locator + "元素找到了!");
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
	 * @param type
	 * @return
	 */
	public boolean verifyElementIsPresent(String locator, String type) {
		type = type.toLowerCase();
		List<WebElement> els = getElementsList(locator, type);
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
	public void close() {
		driver.quit();
	}

}