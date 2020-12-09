package com.arbormetrix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class StudentRegistrationForm {

    WebElement FirstName;
    WebElement MiddleName;
    WebElement LastName;
    WebElement StudentEmail;
    Select Courses;
    private WebElement btnSubmitApplication;
    private WebDriver driver;

    //Object Properties
    private By ByFirstName = By.xpath("//label[normalize-space()='First Name']/preceding-sibling::input");
    private By ByMiddleName = By.xpath("//label[normalize-space()='Middle Name']/preceding-sibling::input");
    private By ByLastName = By.xpath("//label[normalize-space()='Last Name']/preceding-sibling::input");
    private By ByStudentEmail = By.xpath("//label[normalize-space()='example@example.com']/preceding-sibling::input");
    
    //---------------------------------------------------------------------------------------------
    //Constructor
    public StudentRegistrationForm(WebDriver driver) {
    	this.driver = driver;
    	//PageFactory.initElements(driver, this);
    	FirstName = driver.findElement(ByFirstName);
    	MiddleName = driver.findElement(ByMiddleName);
    	LastName = driver.findElement(ByLastName);
    	StudentEmail = driver.findElement(ByStudentEmail);
    }
    
    //---------------------------------------------------------------------------------------------
    //Setters
	public void setFirstName(String firstName) {
		FirstName.sendKeys(firstName);

	}

	public void setMiddleName(String middleName) {
		MiddleName.sendKeys(middleName);
	}

	public void setLastName(String lastName) {
		LastName.sendKeys(lastName);
	}

	public void setStudentEmail(String studentEmail) {
		StudentEmail.sendKeys(studentEmail);;
	}
	
	//Methods
	public boolean isFirstNameDisplayed() {
		return FirstName.isDisplayed();

	}
	public boolean isFirstNameEnabled() {
		return FirstName.isEnabled();

	}
	public void clearFirstName() {
		FirstName.clear();
	}	
	public String getFirstNameText() {
		return FirstName.getText();

	}
	public void SubmitApplication() {
		btnSubmitApplication.click();;
	}
    
    
}
