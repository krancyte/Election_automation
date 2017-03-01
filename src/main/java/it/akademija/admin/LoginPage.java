package it.akademija.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	@FindBy(linkText = "Prisijungti")
	WebElement menuLogin;
	
	@FindBy(id = "login-input")
	WebElement fieldLogin;
	
	@FindBy(id = "password-input")
	WebElement fieldPassword;
	
	@FindBy(id = "login-button")
	WebElement buttonLogin;
	
	@FindBy(className = "alert")
	WebElement alert;
	
	@FindBy(partialLinkText = "Rinkimų Sistema")
	WebElement textElection;
	
	@FindBy(linkText = "none")
	WebElement textNoneLoggedIn;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	protected void login(String username, String password){
		fieldLogin.clear();
		fieldLogin.sendKeys(username);
		fieldPassword.clear();
		fieldPassword.sendKeys(password);
		buttonLogin.click();
	}
	
	protected void clickToLogout() {
		driver.findElement(By.partialLinkText("Atsijungti")).click();
	}
}
