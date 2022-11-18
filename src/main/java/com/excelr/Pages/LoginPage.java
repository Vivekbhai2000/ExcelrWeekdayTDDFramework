package com.excelr.Pages;

import java.io.IOException;

import org.openqa.selenium.By;

import com.excelr.Bases.OHRMBase;

public class LoginPage extends OHRMBase {
	
	
	public String getTitle() {
		return driver.getTitle();	
	}
	
	public boolean getLogo() {
		return driver.findElement(By.xpath("/html/body/div/div[1]/div/div[1]/div/div[1]")).isDisplayed();
		
	}
	public boolean getLoginPage() throws IOException {
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		captureScreenshot("Login");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		return driver.findElement(By.xpath("//span[text()='Dashboard']")).isDisplayed();
		
	}
	public boolean ForgotPassword() {
		driver.findElement(By.xpath("//p[text()='Forgot your password? ']")).click();
		return driver.findElement(By.xpath("//h6[text()='Reset Password']")).isDisplayed();
		
	}

}
