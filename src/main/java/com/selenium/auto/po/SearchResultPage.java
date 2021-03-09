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

	public SearchResultPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

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