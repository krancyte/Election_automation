package it.akademija.admin;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.DataReader;
import utilities.Utilities;

public class RepresentativePage  {

	WebDriver driver;
	private Utilities utilities;

	@FindBy(id = "representative-button")
	WebElement menuRepresentatives;

	@FindBy(id = "register-button")
	WebElement buttonRegister;

	@FindBy(id = "name-input")
	WebElement fieldRepresentativeName;

	@FindBy(id = "surname-input")
	WebElement fieldRepresentiveSurname;

	@FindBy(id = "district-select")
	WebElement dropDownListDistricts;

	@FindBy(id = "create-button")
	WebElement buttonSubmit;

	@FindBy(className = "alert")
	WebElement alert;

	public RepresentativePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		utilities = new Utilities(driver);
	}

	private Select dropdownDistrict;
	private List<String> representativeName;
	private List<String> representativeSurname;
	private List<String> district;
	private Iterator<String> representativeNameIterator;
	private Iterator<String> representativeSurnameIterator;
	private Iterator<String> districtIterator;

//	private String alertLine;
//	private List<String> representativeLogin;
//	private List<String> representativePassword;
	private String name;
	private String surname;

	protected void registerDistrict(String name, String surname, String district) {
		menuRepresentatives.click();
		buttonRegister.click();
		fieldRepresentativeName.sendKeys(name);
		fieldRepresentiveSurname.sendKeys(surname);
		dropdownDistrict = new Select(dropDownListDistricts);
		dropdownDistrict.selectByVisibleText(district);
		buttonSubmit.click();
		// getRepresentativesLoginAndPassword(driver);
	}

	protected void registerMultipleDistricts(String representativeNamesFile, String representativeSurnamesFile,
			String districtFile) throws IOException {
		DataReader dReader = new DataReader();
		representativeName = dReader.getTestData(representativeNamesFile);
		representativeSurname = dReader.getTestData(representativeSurnamesFile);
		district = dReader.getTestData(districtFile);

		representativeNameIterator = representativeName.iterator();
		representativeSurnameIterator = representativeSurname.iterator();
		districtIterator = district.iterator();

		while (representativeNameIterator.hasNext() && representativeSurnameIterator.hasNext() && districtIterator.hasNext()) {
			name = representativeNameIterator.next();
			surname = representativeSurnameIterator.next();
			registerDistrict(name, surname, districtIterator.next());
			utilities.waitToLoad("//*[@id='alert-success-fixed']");
			Assert.assertTrue(alert.getText().contains("Apylinkės atstovas " + name + " " + surname + " sukurtas."));
		}
	}

	// protected void getRepresentativesLoginAndPassword(WebDriver driver){
	// alertLine = driver.findElement(By.xpath("//div[contains(text(),'Vartotojo
	// prisijungimo vardas:')]/following::text()")).getText();
	// String[] mytext = alertLine.split("Vartotojo prisijungimo vardas:");
	// System.out.println("I am text after Vartotojo prisijungimo vardas:: "+
	// mytext[1]);
	//
	// }
}
