package it.akademija.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.google.common.eventbus.AllowConcurrentEvents;

import utilities.Utilities;

public class ResultsPage {
	
	private WebDriver driver;
	private Utilities utilities;
	
	@FindBy(id = "results-button")
	WebElement menuResults;

	@FindBy(xpath = "//*[@id='page-wrapper']//li[2]/a")
	WebElement buttonSingleMember;
	
	@FindBy(id = "result-refresh")
	WebElement buttonRefresh;
	
	@FindBy(id = "confirm-delete-button-undefined")
	WebElement buttonDelete;
	
	@FindBy(xpath = "//*[@id='confirmationModalundefined']/div/div/div[2]/div/button[1]")
	WebElement buttonDeleteConfirmation;
	
	@FindBy(id = "alert-danger-fixed")
	WebElement alertMessage;
	
	
	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		utilities = new Utilities(driver);
	}

	public void deleteResultsInSingleMember() {
		menuResults.click();
		buttonSingleMember.click();
		buttonRefresh.click();
		buttonDelete.click();
		System.out.println("pries waita");
		
		utilities.waitToLoad("//*[@id='confirmationModalundefined']/div/div/div[2]/div/button[1]");
		System.out.println("kiekis: " + driver.findElements(By.xpath("//*[@id='confirmationModalundefined']/div/div/div[2]/div/button[1]")).size());
		System.out.println("pries paspaudima");
		buttonDeleteConfirmation.click();
		System.out.println("po paspaudima");
		utilities.waitToLoad("//*[@id = 'alert-danger-fixed']");
		Assert.assertTrue(alertMessage.getText().equals("Aguonų balsai atmesti."));
	}
	
}
