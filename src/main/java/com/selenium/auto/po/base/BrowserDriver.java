package com.selenium.auto.po.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/*
 * 封装selenium里的方法，使之成为自己的通用方法
 * 封装一个查找单个元素的方法getelement
 * 封装一个查找多个元素的方法getelementlist
 * 封装一个隐式等待时间
 * 封装一个显示等待时间：等待元素显示，出现后点击
 * 封装一个显示等待时间：等待元素可点击
 * @author   : Joe-Tester
 * 
 */
public class BrowserDriver {

	// 创建一个获取驱动的静态方法
	public WebDriver getBrowser() {

		WebDriver driver = null;
		// 读取配置文件
		Properties properties = new Properties();
		try {

			// 加载工程的配置文件
			properties.load(new FileInputStream(System.getProperty("user.dir")
					+ "/src/test/resources/config.properties"));
			// 读取配置文件的关键字
			String browerName = properties.getProperty("brower");

			// 根据配置文件的浏览器类型来创建驱动程序
			if ("chrome".equals(browerName)) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir")
								+ "/src/test/resources/chromedriver.exe");
				driver = new ChromeDriver();
			} else if ("firefox".equals(browerName)) {
				System.setProperty("webdriver.firefox.bin",
						properties.getProperty("ffpath"));
				driver = new FirefoxDriver();
			} else if ("ie".equals(browerName)) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir")
								+ "/src/test/resources/IEDriverServer.exe");
				DesiredCapabilities capabilities = new DesiredCapabilities();
				// ie比较特别，需要忽略它的安全等级设置
				capabilities
						.setCapability(
								InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
								true);
				capabilities.setCapability(
						InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				driver = new InternetExplorerDriver(capabilities);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 设置全局隐式等待时间 || 有显示等待隐式等待就不那么有效了
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

}
