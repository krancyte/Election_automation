package it.akademija.admin;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import org.testng.Assert;

public class LoginAdministratorTest extends VotingSystem{

	/**
	 * TC02
	 */
	@Parameters({ "usernameAdmin", "wrongPassword" })
	@Test(priority = 1)
	public void wrongAdministratorLogin(String usernameAdmin, String password) {
		pageLogin.login(usernameAdmin, password);
		Assert.assertTrue(pageLogin.alert.getText().contains("Dėmesio! Blogai įvestas"));
	}

	/**
	 * TC01
	 */
	@Parameters({ "usernameAdmin", "password" })
	@Test(priority = 2)
	public void loginAdministratorTest(String usernameAdmin, String password) {
		pageLogin.login(usernameAdmin, password);
		Assert.assertTrue(pageLogin.textElection.isDisplayed());
	}

	/**
	 * TC03
	 */
	@Test(priority = 50, enabled = true)
	public void logoutAdministrator() {
		pageLogin.clickToLogout();
		Assert.assertTrue(pageLogin.textNoneLoggedIn.isDisplayed());
	}
}
