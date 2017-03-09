package it.akademija.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {

	WebDriver driver;

	@FindBy(linkText = "Prisijungti")
	WebElement menuLogin;
	
	@FindBy(id = "login-input")
	WebElement fieldUser;
	
	@FindBy(id = "password-input")
	WebElement fieldPassword;
	
	@FindBy(id = "login-button")
	WebElement buttonLogin;
	
	@FindBy(className = "alert")
	WebElement alert;
	
	@FindBy(id = "logout-button")
	WebElement buttonLogout;
	
	@FindBy(linkText = "Pradinis")
	WebElement textPradinis;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void login(String username, String password){
		fieldUser.clear();
		fieldUser.sendKeys(username);
		fieldPassword.clear();
		fieldPassword.sendKeys(password);
		buttonLogin.click();
	}
	
	public void logout() {
		buttonLogout.click();
		Assert.assertTrue(textPradinis.isDisplayed());
	}
}
