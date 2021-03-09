package com.joe.test.pagefactory.demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 
 * 注解@Parameters优化脚本
 * 
 * @author Joe-Tester
 *
 */
public class test_parameters {
	WebDriver driver = null;
	public String starturl;
	public String browser;
	public String content;

	/**
	 * 使用testng框架套件，在xml文件中定义parameter标签及值
	 * 
	 * @param url
	 * @param br
	 * @throws InterruptedException
	 */
	@Parameters({ "url", "browser", "content" })
	@BeforeTest
	public void bf(String url, String br, String text)
			throws InterruptedException {
		starturl = url;
		browser = br;
		content = text;
	}

	@Test
	public void test_param_demo() throws InterruptedException {
		// 按条件启动程序
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")
							+ "/src/test/resources/chromedriver.exe");
			driver = new ChromeDriver();
		}
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