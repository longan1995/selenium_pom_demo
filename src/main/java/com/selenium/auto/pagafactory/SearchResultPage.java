package com.selenium.auto.pagafactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

/**
 * 在结果页面进行操作
 * 
 * @author Joe-Tester
 *
 */
public class SearchResultPage extends BasePage {
	
    private final int TIMEOUT = 10;    

	// 搜索结果，第一列
	@FindBy(xpath = "//*[@id='1']//a[1]/em")
	@CacheLookup
	public WebElement result_text;

	// 默认构造函数
	public SearchResultPage() {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,TIMEOUT), this);
	}

	/**
	 * 校验搜索结果
	 */
	public void checkSearchResult() {
		System.out.println(result_text.getText());
	}

	/**
	 * 点击搜索某一结果跳转详情页
	 */
	public void goToResultDetails() {
		result_text.click();
	}
}