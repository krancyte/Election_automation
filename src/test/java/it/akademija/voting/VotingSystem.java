package it.akademija.voting;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import it.akademija.admin.CountyPage;
import it.akademija.admin.DistrictPage;
import it.akademija.admin.LoginPage;
import it.akademija.admin.PartyPage;
import it.akademija.admin.RepresentativePage;
import it.akademija.admin.ResultsPage;
import it.akademija.representative.MultiMemberPage;
import it.akademija.representative.RepresentativeViewPage;
import it.akademija.representative.SingleMemberPage;
import utilities.Utilities;


public abstract class VotingSystem {
	
	protected WebDriver driver;
	protected LoginPage pageLogin;
	protected CountyPage pageCounty;
	protected PartyPage pageParty;
	protected DistrictPage pageDistrict;
	protected RepresentativePage pageRepresentative;
	//DeletionOfAllData 
	
	protected SingleMemberPage pageSingleMember;
	protected MultiMemberPage pageMultiMember;
	protected ResultsPage pageResults;
	protected Utilities utilities;
	//protected RepresentativeViewPage pageRepresentativeView;

	@Parameters({"browser", "usernameAdmin", "password", "loginLink"})
	@BeforeClass
	public void settingUp(String browser, String usernameAdmin, String password, String loginLink) throws Exception {
		if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\User\\workspace\\Projektas\\Election_automation\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "C:\\Users\\User\\workspace\\Projektas\\Election_automation\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("opera")){
			System.setProperty("webdriver.opera.driver", "C:\\Users\\User\\workspace\\Projektas\\Election_automation\\operadriver.exe");
			driver = new OperaDriver();
		}else {
			throw new Exception("Browser is not correct");
		}
		driver.manage().window().maximize();	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get(loginLink);
		pageLogin = new LoginPage(driver);
	//	pageLogin.login(usernameAdmin, password);
//		switch (timesRan) {
//		case 0:
//			driver.get(adminLink);
//			pageLogin = new LoginPage(driver);
//			break;
//		case 1:
//			driver.get(adminLink);
//		//	pageCounty = new CountyPage(driver);
//			break;
//		case 2:
//			driver.get(adminLink);
//			pageDistrict = new DistrictPage(driver);
//			break;
//		case 3:
//			driver.get(adminLink);
//			pageRepresentative = new RepresentativePage(driver);
//			break;
//		case 4:
//			driver.get(adminLink);
////			pageParty = new PartyPage(driver);
//			break;
//		case 5:
//			driver.get(adminLink);
//			pageCandidates = new CandidatesPage(driver);
//			break;
//		case 6:
//			driver.get(representativeLink);
//			pageSingleMember = new SingleMemberPage(driver);
//			
//			break;
//		case 7:
//			driver.get(representativeLink);
//			pageMultiMember = new MultiMemberPage(driver);
//			break;
//		case 8:
//			driver.get(adminLink);
//			pageResults = new ResultsPage(driver);
//			break;
//		default:
//			break;
//			
//			
//		}
////		if (timesRan == 0){
////			driver.get(link);
////			
////		} else {
////			driver.get(adminLink);
////		}
//		timesRan++;
	}
	
	@AfterClass
	public void endingTestActivities() {
		
	//	driver.close();
	
	}
	

}
