package it.akademija.admin;

import java.io.IOException;


import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.DataReader;
import utilities.Utilities;

public class DistrictPage {

	WebDriver driver;
	private int districtRow;
	private Utilities utilities;

	@FindBy(id = "district-button")
	WebElement menuDistricts;

	@FindBy(id = "register-button")
	WebElement buttonRegister;

	@FindBy(id = "name-input")
	WebElement fieldDistrictName;

	@FindBy(id = "adress-input")
	WebElement fieldAddress;

	@FindBy(id = "voters-input")
	WebElement fieldVoters;

	@FindBy(id = "county-district-select")
	WebElement dropDownListCounties;

	@FindBy(id = "create-button")
	WebElement buttonSubmit;

	@FindBy(className = "alert")
	WebElement alert;
	
	@FindBy(className = "btn")
	WebElement buttonToConfirmDeleting;
	
	@FindBy(xpath = "//*[@id='searchable-table_filter']/label/input")
	WebElement fieldSearch;
	
	@FindBy(xpath = "//*[contains(@id, 'edit-district')]")
	WebElement buttonEdit;
	
	@FindBy(xpath = "//*[contains(@id, 'confirm-delete-button')]")
	WebElement buttonDelete;
	
	public DistrictPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		utilities = new Utilities(driver);
	}

	private Select dropdownCounty;
	private List<String> districtName;
	private List<String> districtAddress;
	private List<String> districtVoters;
	private List<String> countyName;
	private Iterator<String> districtAddressIterator;
	private Iterator<String> districtVotersIterator;
	private Iterator<String> countyNameIterator;
	private Iterator<String> districtNameIterator;

	protected void registerDistrict(String districtName, String districtAddress, String districtVoters, String county, String enterOrEdit) {
		if (!enterOrEdit.equals("edit")){
			menuDistricts.click();
			buttonRegister.click();
		} else {
			clearTheField(fieldDistrictName);
		}
		fieldDistrictName.sendKeys(districtName);
		if (enterOrEdit.equals("edit")){
			clearTheField(fieldAddress);
		}
		fieldAddress.sendKeys(districtAddress);
		if (enterOrEdit.equals("edit")){
			clearTheField(fieldVoters);
		}
		fieldVoters.sendKeys(districtVoters);
		dropdownCounty = new Select(dropDownListCounties);
		dropdownCounty.selectByVisibleText(county);
		buttonSubmit.click();
	}

	protected void registerMultipleDistricts(String districtFile, String districtAddressFile, String districtVotersFile,
			String county, boolean isCountyPlural) throws IOException {
		DataReader dReader = new DataReader();
		districtName = dReader.getTestData(districtFile);
		districtAddress = dReader.getTestData(districtAddressFile);
		districtVoters = dReader.getTestData(districtVotersFile);
		districtNameIterator = districtName.iterator();
		districtAddressIterator = districtAddress.iterator();
		districtVotersIterator = districtVoters.iterator();
		if (isCountyPlural) {
			countyName = dReader.getTestData(county);
			countyNameIterator = countyName.iterator();
		}
		while (districtNameIterator.hasNext() && districtAddressIterator.hasNext()
				&& districtVotersIterator.hasNext()) {
			String district = districtNameIterator.next();
			if (isCountyPlural) {
				registerDistrict(district, districtAddressIterator.next(), districtVotersIterator.next(),
						countyNameIterator.next(), "");
			} else {
				registerDistrict(district, districtAddressIterator.next(), districtVotersIterator.next(), county, "");
			}
			utilities.waitToLoad("//*[@id='alert-success-fixed']");
			Assert.assertTrue(alert.getText().contains("Apylinkė " + district + " sukurta"));
		}
	}

	protected void editDistrict(String districtName, String newDistrictName, String newDistrictAddress, String newDistrictVoters, String newCounty) {
		districtRow = utilities.findElementForDeletingAndEditing(menuDistricts, districtName);
		//	utilities.waitForJavascript();
			driver.findElement(By.xpath("//tbody/tr[" + districtRow + "]//a[1]")).click();
			registerDistrict(newDistrictName, newDistrictAddress, newDistrictVoters, newCounty, "edit");
	}
	
	protected void clearTheField(WebElement fieldName){
		fieldName.clear();	
	}

	public void deleteDistrict(String districtName) {
		menuDistricts.click();
		utilities.waitToLoad("//*[@id='searchable-table_filter']/label/input").sendKeys(districtName);
		buttonDelete.click();
		utilities.waitToLoad("//*[starts-with(@id, 'delete-district')]").click();
		
	}

}
