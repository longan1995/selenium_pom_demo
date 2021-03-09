package com.joe.test.pagefactory.demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * 这是一个线性脚本：从创建浏览器驱动，操作元素得到结果到关闭浏览器；完成一个测试用例。
 * 
 * @author Joe-tester
 *
 */
public class test_linear_script {

	// 初始化driver对象
	WebDriver driver = null;
	// 测试目标地址
	public String baidu_url = "https://www.baidu.com";

	@Test
	public void test_baidu_demo() {
		// 设置系统属性，读取chrome浏览器驱动程序
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")
						+ "/src/test/resources/chromedriver.exe");
		// 创建驱动
		driver = new ChromeDriver();
		System.out.println("启动浏览器打开网址：" + baidu_url);
		// 浏览器输入地址
		driver.get(baidu_url);
		// 浏览器最大化及设置隐式等待时间
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// 查找元素并输入文本类容
		System.out.println("文本框输入查询内容");
		driver.findElement(By.id("kw")).sendKeys("selenium demo");
		// 点击搜索按钮
		System.out.println("点击百度一下按钮");
		driver.findElement(By.id("su")).click();
		// 退出浏览器，close只是关闭当前tab的窗口
		driver.quit();

	}

}