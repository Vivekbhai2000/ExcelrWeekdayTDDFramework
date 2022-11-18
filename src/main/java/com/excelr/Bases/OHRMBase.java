package com.excelr.Bases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OHRMBase {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public  void Intialization() {
		
		LoadPropertiesFile();
		String browserName = prop.getProperty("browser");
		
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if(browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	//	WebDriverManager.firefoxdriver().setup();
	//	driver = new FirefoxDriver();
	//	driver.manage().window().maximize();
	//	driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	public void tearDown() {
		driver.quit();
	}
	
	public void LoadPropertiesFile() {
		FileInputStream fis;
		String configFilePath = "./src/main/java/com/excelr/config/config.properties";
		try {
			fis = new FileInputStream(configFilePath);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	public void captureScreenshot(String testcaseName) throws IOException {
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = "./Screenshots/" + testcaseName + ".jpg";
		try {FileUtils.copyFile(file, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
