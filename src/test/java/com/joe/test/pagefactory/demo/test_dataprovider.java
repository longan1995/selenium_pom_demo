package com.joe.test.pagefactory.demo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 使用@DataProvider注解来解决数据问题，假死搜索的类容有N多数据
 * 
 * @author Joe-Tester
 *
 */
public class test_dataprovider {
	// 初始化驱动实例（对象）
	WebDriver driver = null;
	public String starturl = "https://www.baidu.com";

	@DataProvider(name = "select")
	public Object[][] execleread() throws IOException {
		// 返回一个二维数组，{ { "selenium" , "appium" } }这是两列，需要在测试用例中闯入两列参数，与下面数据不一样
		return new Object[][] { { "selenium" }, { "appium" } };
	}

	@Test(dataProvider = "select")
	public void test_search_baidu(String content) throws InterruptedException {
		// 设置系统属性，启动浏览器驱动
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")
						+ "/src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		// 打开起始测试地址，窗口最大化及设置隐式等待时间
		driver.get(starturl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// 输入文本内容
		driver.findElement(By.id("kw")).sendKeys(content);
		// 点击百度一下按钮
		driver.findElement(By.id("su")).click();
		// 线程强制等待2s
		Thread.sleep(2000);
		// 关闭浏览器
		driver.quit();
	}
}