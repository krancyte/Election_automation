package it.akademija.admin;

import org.testng.annotations.Test;

import it.akademija.representative.MultiMemberPage;
import it.akademija.representative.SingleMemberPage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;

public class LoginAdministratorTest extends VotingSystem{

	

	/**
	 * TC02
	 */
	@Parameters({ "usernameAdmin", "wrongPassword" })
	@Test(priority = 1)
	public void wrongAdministratorLogin(String usernameAdmin, String password) {
		pageLogin.login(usernameAdmin, password);
		System.out.println("prisijungimo vardas arba");
		System.out.println("Dėmesio!");
		System.out.println(pageLogin.alert.getText());
		Assert.assertTrue(pageLogin.alert.getText().contains("Blogai įvestas prisijungimo vardas"));
	}

	/**
	 * TC01
	 */
	@Parameters({ "usernameAdmin", "password" })
	@Test(priority = 2)
	public void loginAdministratorTest(String usernameAdmin, String password) {
		pageLogin.login(usernameAdmin, password);
		Assert.assertTrue(pageLogin.buttonLogout.isDisplayed());
	}

	/**
	 * TC03
	 */
	@Test(priority = 50, enabled = false)
	public void logoutAdministrator() {
		pageLogin.clickToLogout();
		Assert.assertTrue(pageLogin.textPradinis.isDisplayed());
	}
}
