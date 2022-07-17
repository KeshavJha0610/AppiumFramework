package org.academy.TestUtils;

import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.academy.pageObjects.ios.HomePage;
import org.academy.utils.AppiumUtils;
import org.testng.annotations.AfterClass;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class IOSBaseTest extends AppiumUtils{

	public IOSDriver driver;
	public AppiumDriverLocalService service;
	public HomePage homePage;
	
	@BeforeClass
	public void ConfigureAppium() throws IOException
	{
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//org//rahulshettyacademy//resources//data.properties");
				
		prop.load(fis);
		String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
			
		service = startAppiumServer(ipAddress,Integer.parseInt(port));
			
				XCUITestOptions	 options = new XCUITestOptions();	
				options.setDeviceName("iPhone 13 Pro");
				options.setApp("/Users/rahulshetty/Desktop/UIKitCatalog.app");
			//	options.setApp("//Users//rahulshetty//workingcode//Appium//src//test//java//resources//TestApp 3.app");
				options.setPlatformVersion("15.5");
				//Appium- Webdriver Agent -> IOS Apps.
				options.setWdaLaunchTimeout(Duration.ofSeconds(20));
				
			 driver = new IOSDriver(service.getUrl(), options);
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			 homePage = new HomePage(driver);
			 
	}
	
	
	

	
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
        service.stop();
		}
	
}
