package it.akademija.representative;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Utilities;

public class MultiMemberPage {

	private WebDriver driver;
	private int numberOfRows;
	private Utilities utilities;
	private int number;
	private SingleMemberPage pageSingleMember;
	private int numberOfVoters;
	private int numberOfPeopleWhoVotedInMultiMember;
	private String votesNumber;

	@FindBy(linkText = "Atstovas")
	WebElement menuRepresentative;
	//
	// @FindBy(xpath = "//*[@id='side-menu']/li[2]/a")
	// WebElement menuRepresentativePanel;

	@FindBy(xpath = "//*[@id='side-menu']/li[4]/a")
	WebElement menuMultiMember;

	@FindBy(xpath = "//*[@id='basic-addon2']")
	List<WebElement> rows;

	@FindBy(xpath = "//form/button")
	WebElement buttonSubmit;

	@FindBy(className = "alert")
	public WebElement alertMessage;
	
	@FindBy(partialLinkText = "Atsijungti")
	public WebElement buttonLogoutRepresentative;

	public MultiMemberPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		utilities = new Utilities(driver);
		pageSingleMember = new SingleMemberPage(driver);
	}
	
	public void fillOutResultRows() {
		
		System.out.println("eiluciu pries : " + numberOfRows);
		numberOfVoters = pageSingleMember.getTheNumberOfPeopleWhoVoted();
		System.out.println("single member balsavo: " + numberOfVoters);
		//numberOfVoters = pageRepresentativeView.getElectorsNumber();
		menuMultiMember.click();
		utilities.waitToLoad("//*[@id='rating-modal-1']");
		numberOfRows = rows.size();
	//	numberOfRows = 6;
		System.out.println("eiluciu: " + numberOfRows);

		System.out.println("number : " + numberOfVoters / numberOfRows);

		for (int i = numberOfRows; i > 1; i--) {

			number = utilities.randomNumber(numberOfVoters / numberOfRows);
			numberOfPeopleWhoVotedInMultiMember += number;
			System.out.println("number galutinis: " + number);
			votesNumber = Integer.toString(number);
			driver.findElement(By.xpath(".//form/div[" + i + "]/div[3]/input")).sendKeys(votesNumber);
		}
		number = numberOfVoters - numberOfPeopleWhoVotedInMultiMember;
		votesNumber = Integer.toString(number);
		driver.findElement(By.xpath(".//form/div[1]/div[3]/input")).sendKeys(votesNumber);
		buttonSubmit.click();
	//	utilities.waitToLoad("//*[@id='alert-success']");
		
	}

}
