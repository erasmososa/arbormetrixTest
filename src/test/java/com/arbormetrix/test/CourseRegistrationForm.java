package com.arbormetrix.test;
import com.arbormetrix.pages.StudentRegistrationForm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CourseRegistrationForm {

	private WebDriver chromeDriver;
	
	@Before
	public void setUp() {
		
		String projectLocation = System.getProperty("user.dir");
		System.out.println("projectLocation: " + projectLocation);
		
		System.setProperty("webdriver.chrome.driver","./src/test/resources/drivers/chromedriver.exe");
				
		//Launch a new chrome instance
		chromeDriver = new ChromeDriver();
		
		//Maximize the browser window
		chromeDriver.manage().window().maximize();
		
		//define implicit wait
		chromeDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;		
		
		//navigate to google
		chromeDriver.get("https://www.jotform.com/form-templates/class-registration-3");
		
		System.out.println(chromeDriver.getTitle());
		
		//Switch to Virtual Document
		chromeDriver.switchTo().defaultContent();
		WebElement objRegForm = chromeDriver.findElement(By.id("formPreviewArea"));
		System.out.println("Registration Form isDisplayed: " + objRegForm.isDisplayed());
		System.out.println("Registration Form isEnabled: " + objRegForm.isEnabled());
		chromeDriver.switchTo().frame(objRegForm);

	}
		
	@Test
	public void arbormetrixTest() {
		StudentRegistrationForm StudentRegFormPage = new StudentRegistrationForm(chromeDriver);
		
		final String strFirstName = "Erasmo";
		final String strMiddleName = "Manuel";
		final String strLastName = "Sosa";
		final String strStudentEmail = "erasmo.m.sosa@gmail.com";
		final String strCourseSelection = "Math 101";
		Boolean DropdownOptionIsFound = false;
		
		//Generic Wait
		WebDriverWait GenericWait = new WebDriverWait(chromeDriver, 20);
		
		//Objects Identication Definition
		//By ByFirstName = By.xpath("//label[normalize-space()='First Name']/preceding-sibling::input");
		By ByMiddleName = By.xpath("//label[normalize-space()='Middle Name']/preceding-sibling::input");
	    By ByLastName = By.xpath("//label[normalize-space()='Last Name']/preceding-sibling::input");
	    By ByStudentEmail = By.xpath("//label[normalize-space()='example@example.com']/preceding-sibling::input");
	    By ByCourses = By.xpath("//label[normalize-space()='Courses']/following::select[1]");
	    By BySubmitApplication = By.xpath("//button[text()='Submit Application']");
	    By ByConfirmationMsg = By.xpath("//p[text()='Your submission has been received.']");
	    By ByStudentRegForm = By.xpath("//h2[text()='Student Registration Form']");
	    
	    //Objects Declaration
	    //WebElement txtFirstName = chromeDriver.findElement(ByFirstName);
	    WebElement txtMiddleName = chromeDriver.findElement(ByMiddleName);
	    WebElement txtLastName = chromeDriver.findElement(ByLastName);
	    WebElement txtStudentEmail = chromeDriver.findElement(ByStudentEmail);
	    Select DropdownCourses = new Select(chromeDriver.findElement(ByCourses));
	    WebElement BtnSubmitApplication = chromeDriver.findElement(BySubmitApplication);
	    
		//Verify fields are displayed
		//Assert.assertEquals(true, txtFirstName.isDisplayed());
		//System.out.println("txtFirstName is displayed");
		//StudentRegFormPage.isFirstNameDisplayed();
		Assert.assertEquals(true, StudentRegFormPage.isFirstNameDisplayed());
		System.out.println("FirstName is displayed");
		
		Assert.assertEquals(true, txtMiddleName.isDisplayed());
		System.out.println("MiddleName is displayed");
		
		Assert.assertEquals(true, txtLastName.isDisplayed());
		System.out.println("LastName is displayed");
		
		//Verify fields are enabled
		//Assert.assertEquals(true, txtFirstName.isEnabled());
		//System.out.println("FirstName is enabled");
		Assert.assertEquals(true, StudentRegFormPage.isFirstNameEnabled());
		System.out.println("FirstName is enabled");
		
		Assert.assertEquals(true, txtMiddleName.isEnabled());
		System.out.println("MiddleName is enabled");
		
		Assert.assertEquals(true, txtLastName.isEnabled());
		System.out.println("LastName is enabled");
		
		
		//clear if existing text value
		//txtFirstName.clear();
		StudentRegFormPage.clearFirstName();
		txtMiddleName.clear();
		txtLastName.clear();
		
		//Verify txtObj text is clear
		//Assert.assertEquals("", txtFirstName.getText());
		//System.out.println("txtFirstName text is cleared");
		Assert.assertEquals("",StudentRegFormPage.getFirstNameText());
		System.out.println("FirstName text is cleared");
		
		
		Assert.assertEquals("", txtMiddleName.getText());
		System.out.println("MiddleName text is cleared");
		
		Assert.assertEquals("", txtLastName.getText());
		System.out.println("LastName text is cleared");
		
		//Enter field values
		
		//txtFirstName.sendKeys(strFirstName);
		//txtMiddleName.sendKeys(strMiddleName);
		//txtLastName.sendKeys(strLastName);
		
		StudentRegFormPage.setFirstName(strFirstName);
		StudentRegFormPage.setMiddleName(strMiddleName);
		StudentRegFormPage.setLastName(strLastName);
		
		//-------------------------------------------------------------------------------
		//5. Assert Student E-mail is present
		//Verify fields are displayed
		Assert.assertEquals(true, txtStudentEmail.isDisplayed());
		System.out.println("StudentEmail is displayed");

		//Verify fields are enabled
		Assert.assertEquals(true, txtStudentEmail.isEnabled());
		System.out.println("StudentEmail is enabled");

		//-------------------------------------------------------------------------------
		//6. Insert your email address into field
		txtStudentEmail.sendKeys(strStudentEmail);
		System.out.println("StudentEmail value is set: " + strStudentEmail);

		//-------------------------------------------------------------------------------
		//7. Select Courses dropdown and assert that Math 101 is present
		List<WebElement> CourseOptions = DropdownCourses.getOptions();
		int CoursesCount = CourseOptions.size();
		for (int i=0; i < CoursesCount;i++) {
			//System.out.println(CourseOptions.get(i).getText());
			if(CourseOptions.get(i).getText().trim().contains(strCourseSelection)) {
				DropdownOptionIsFound = true;
				break;
			}
		}
		
		Assert.assertEquals(true, DropdownOptionIsFound);
		System.out.println("Course Selection value is available for selection: " + strCourseSelection);
		
		DropdownCourses.selectByValue("Math 101");
		
		//-------------------------------------------------------------------------------		
		//8. Submit Application
		BtnSubmitApplication.click();
		System.out.println("Submit button is clicked");
		
		//-------------------------------------------------------------------------------				
		//9. Wait for redirect
		GenericWait.until(ExpectedConditions.visibilityOfElementLocated(ByConfirmationMsg));
		System.out.println("Confirmation Msg is displayed");
		
		//10. Assert Student Registration Form is present after redirect
		GenericWait.until(ExpectedConditions.invisibilityOfElementLocated(ByConfirmationMsg));
		System.out.println("Confirmation Msg is gone");
		
		chromeDriver.switchTo().defaultContent();
		WebElement objRegForm = chromeDriver.findElement(By.id("formPreviewArea"));
		System.out.println("Registration Form isDisplayed: " + objRegForm.isDisplayed());
		System.out.println("Registration Form isEnabled: " + objRegForm.isEnabled());
		chromeDriver.switchTo().frame(objRegForm);
		
		WebElement labelStudentRegForm = chromeDriver.findElement(ByStudentRegForm);
		Assert.assertEquals(true, labelStudentRegForm.isDisplayed());
		System.out.println("Student Registration Form is Displayed: " + labelStudentRegForm.isEnabled());
			
	}
	
	@After
	public void tearDown() throws Exception{
		//close browser
		chromeDriver.quit();
	}
}
