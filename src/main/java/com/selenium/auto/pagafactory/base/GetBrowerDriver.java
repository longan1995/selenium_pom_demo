package com.selenium.auto.pagafactory.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * 根据配置文件中配置的信息来启动对应浏览器
 * 
 * @author Joe-Tester
 *
 */
public class GetBrowerDriver {

	// 驱动程序待定
	private static WebDriver driver = null;

	// 创建一个获取驱动的静态方法
	public static WebDriver getWebDriver() {
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

	/**
	 * 测试驱动是否正确打开浏览器
	 * 
	 * @param args
	 * @throws InterruptedException
	 *             FileNotFoundException
	 */
	public static void main(String[] args) throws InterruptedException,
			FileNotFoundException {
		// 初始化浏览器驱动
		WebDriver driver = GetBrowerDriver.getWebDriver();
		// 打开浏览器
		driver.get("http://www.baidu.com");
		// 最大化浏览器、设置全局隐式等待时间
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// 线程强制等待2s
		Thread.sleep(2);
		// 把浏览器关闭
		driver.quit();
	}
}
