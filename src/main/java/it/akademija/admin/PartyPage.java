package it.akademija.admin;

import java.awt.peer.ButtonPeer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.Utilities;

public class PartyPage {

	WebDriver driver;
	private int partyRow;
	private Utilities utilities;

	@FindBy(id = "party-button")
	public WebElement menuParties;

	@FindBy(id = "register-button")
	WebElement buttonRegister;

	@FindBy(id = "name-input")
	WebElement fieldPartyName;

	@FindBy(id = "number-input")
	WebElement fieldNumber;

	@FindBy(id = "file-select")
	WebElement buttonFile;

	@FindBy(id = "create-button")
	WebElement buttonSubmit;

	@FindBy(className = "alert")
	public WebElement alert;

	// @FindBy(id = "confirm-delete-button-undefined")
	// WebElement buttonDetele;

	@FindBy(xpath = "//tbody//tr[1]")
	WebElement listCandidates;

	@FindBy(xpath = "//*[@id='searchable-table_filter']/label/input")
	WebElement fieldSearch;

	@FindBy(xpath = "//*[contains(@id, 'edit-button')]")
	WebElement buttonEdit;

	@FindBy(xpath = "//*[contains(@id, 'confirm-delete-button')]")
	WebElement buttonDelete;

	@FindBy(className = "btn")
	WebElement buttonToConfirmDeleting;

	public PartyPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		utilities = new Utilities(driver);
	}

	public void registerParty(String partyName, String partyNumber, String partyFile, String enterOrEdit) {
		if (!enterOrEdit.equals("edit")) {
			menuParties.click();
			buttonRegister.click();
			utilities.attachFile(buttonFile, partyFile);
		}
		if (enterOrEdit.equals("edit")) {
			fieldPartyName.clear();
		}
		fieldPartyName.sendKeys(partyName);
		if (enterOrEdit.equals("edit")) {
			fieldNumber.clear();
		}
		fieldNumber.sendKeys(partyNumber);
		buttonSubmit.click();
	}

	protected void editParty(String partyName, String newPartyName, String newPartyNumber, String file) {
		menuParties.click();

		partyRow = utilities.findElementForDeletingAndEditing(menuParties, partyName, "party");
		driver.findElement(By.xpath("//tbody/tr[" + partyRow + "]//a[1]")).click();
		utilities.waitForJavascript();
		registerParty(newPartyName, newPartyNumber, file, "edit");
	}

	public void deleteParty(String partyName) {

		// utilities.waitToLoad("//*[starts-with(@id,
		// 'delete-button')]").click();
		partyRow = utilities.findElementForDeletingAndEditing(menuParties, partyName, "party");
		if (partyRow != 0) {
			utilities.waitToLoad("//*[@id='register-button']");
			driver.findElement(By.xpath("//tbody/tr[" + partyRow + "]/td[3]/a[2]")).click();
			utilities.waitToLoad("//tr[" + partyRow + "]//button[contains(@id,'delete-button')]").click();
			utilities.waitToLoad("//*[@id='register-button']");
			Assert.assertTrue(alert.getText().contains(partyName + " iðtrinta"));
		} else {
			System.out.println("partija jau istrinta");
		}
		utilities.waitToLoad("//*[@id='register-button']");
		System.out.println("deletinimo pabaiga");
	}

	protected void deleteCandidates(String partyName) {
		System.out.println("kandidatu pradzia");
		partyRow = utilities.findElementForDeletingAndEditing(menuParties, partyName, "party");
		driver.findElement(By.xpath("//tbody/tr[" + partyRow + "]//a[1]")).click();

		buttonDelete.click();
		utilities.waitToLoad("//*[@id='confirmationModalundefined']//div[3]//button[1]").click();
		Assert.assertTrue(alert.getText().contains("Partijos saraðas iðtrintas"));
		
		//utilities.waitForJavascript();
		 utilities.waitToLoad("//*[@id='file-select']");
		// utilities.waitToLoad("//*[@id='alert-danger-fixed']");
		// utilities.waitToLoad("//*[@id='create-button']").click();
		// utilities.waitToLoad("//*[@id='register-button']");

	//	utilities.waitToLoad("//*[@id='create-button']");
	//	buttonSubmit.click();
	//	utilities.waitToLoad("//*[@id='register-button']");

	}

}
