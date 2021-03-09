package com.joe.test.po.demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * 以登录百度，点击搜索selenium webdriver关键信息
 * 
 * @ClassName:LoginSearchClickScene.java
 * @author : Joe-Tester
 */
public class test_linear_script {

	WebDriver driver;

	@BeforeTest
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")
						+ "/src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(2000);
		driver.quit();
	}

	@Test
	public void test_baidu_search() throws Exception {
		driver.get("https://www.baidu.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='u1']/a")).click();
		System.out.println("点击登录");
		Thread.sleep(1000);
		driver.findElement(By.id("TANGRAM__PSP_11__footerULoginBtn")).click();
		System.out.println("点击弹窗的用户名登录");
		WebElement element = driver.findElement(By
				.id("TANGRAM__PSP_11__userName"));
		element.clear();
		Thread.sleep(1000);
		element.sendKeys("xx");
		System.out.println("输入用户名");
		WebElement el = driver.findElement(By.id("TANGRAM__PSP_11__password"));
		el.clear();
		Thread.sleep(1000);
		el.sendKeys("oo");
		System.out.println("输入密码");
		Thread.sleep(1000);
		driver.findElement(By.id("TANGRAM__PSP_11__submit")).click();
		System.out.println("点击登录按钮");
		Thread.sleep(2000);
		driver.findElement(By.id("TANGRAM__PSP_4__closeBtn")).click();
		System.out.println("关闭弹窗");
		driver.findElement(By.id("kw")).sendKeys("selenium webdriver");
		System.out.println("input文本框输入搜索关键字");
		driver.findElement(By.id("su")).click();
		System.out.println("点击百度一下按钮");
	}
}
