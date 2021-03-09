package com.selenium.auto.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 封装上一个页面的结果页面对象
 * 
 * @author Joe-Tester
 *
 */
public class SearchResultPage extends BasePage {

	/**
	 * super()和this()类似,区别是，super()从子类中调用父类的构造方法，this()在同一类内调用其它方法
	 * 
	 * @param driver
	 */
	public SearchResultPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// 测试元素定位器
	By result_text = By.xpath("//*[@id='1']//a[1]/em");

	/**
	 * 校验搜索结果
	 */
	public void checkSearchResult() {
		System.out.println("测试用例执行结果：" + getElement(result_text).getText());
	}

	/**
	 * 点击搜索某一结果跳转详情页
	 */
	public void goToResultDetails() {
		getElement(result_text).click();
	}
}