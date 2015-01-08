package com.johndoll.bluesourceselenium.pages;
 
import com.johndoll.bluesourceselenium.utility.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Jonathan Doll
 */
public class EmployeePage {
    
    private WebDriver driver;
    
    public EmployeePage(WebDriver driver){
        this.driver = driver;
    }
    
    public WebElement btnAdd(){
        return driver.findElement(By.xpath("//button[contains(text(),'Add')]"));
    }
    
    public WebElement username(){
        return driver.findElement(By.id("employee_username"));
    }
    
    public WebElement firstName(){
        return driver.findElement(By.id("employee_first_name"));
    }
    
    public WebElement lastName(){
        return driver.findElement(By.id("employee_last_name"));
    }
    
    public WebElement btnCreateEmployee(){
        return driver.findElement(By.name("commit"));
    }
    
    public boolean createSuccessful(){
        return driver.findElements(By.className("alert-success")).size() > 0;
    }
    
    public boolean createFailure(){
        return driver.findElements(By.className("alert-danger")).size() > 0;
    }
    
    public Boolean welcomeMessage(){
        return driver.findElements(By.xpath("//body//div//section//div//h1")).size() > 0;
    }
    
    public WebElement nameLink(){
        return driver.findElement(By.xpath("//div//h1[contains(text(),'Welcome')]//a"));
    }
    
    public WebElement searchBar(){
        return driver.findElement(By.xpath("//input[@id='search-bar']"));
    }
    
    public WebElement searchFirstName(String firstName){
        return driver.findElement(By.linkText(firstName));
    }
    
    public WebElement searchLastName(String lastName){
        return driver.findElement(By.linkText(lastName));
    }
    
    public boolean searchFirstNameExists(String firstName){
        return driver.findElements(By.linkText(firstName)).size() > 0;
    }
    
    public boolean searchLastNameExists(String lastName){
        return driver.findElements(By.linkText(lastName)).size() > 0;
    }
    
    public WebElement title(){
        return driver.findElement(By.id("employee_title_id"));
    }
    
    public WebElement role(){
        return driver.findElement(By.id("employee_role"));
    }
    
    public WebElement manager(){
        return driver.findElement(By.id("employee_manager_id"));
    }
    
    public WebElement status(){
        return driver.findElement(By.id("employee_status"));
    }
    
    public WebElement bridgeTime(){
        return driver.findElement(By.id("employee_bridge_time"));
    }
    
    public WebElement location(){
        return driver.findElement(By.id("employee_location"));
    }
    
    public WebElement startDate(){
        return driver.findElement(By.id("employee_start_date"));
    }
    
    public WebElement cellPhone(){
        return driver.findElement(By.id("employee_cell_phone"));
    }
    
    public WebElement officePhone(){
        return driver.findElement(By.id("employee_office_phone"));
    }
    
    public WebElement email(){
        return driver.findElement(By.id("employee_email"));
    }
    
    public WebElement imName(){
        return driver.findElement(By.id("employee_im_name"));
    }
    
    public WebElement imClient(){
        return driver.findElement(By.id("employee_im_client"));
    }
    
    public WebElement department(){
        return driver.findElement(By.id("employee_department_id"));
    }
    
    public boolean firstNameTableExists(){
        return driver.findElements(By.linkText("First Name")).size() > 0;
    }
    
    public void createNewEmployee(String username, String firstName, String lastName, String title, String role, String manager, String status, String bridgeTime, String location, String startDate, String cellPhone, String officePhone, String email, String imName, String imClient, String department){
        btnAdd().click();
        
        long timer = System.currentTimeMillis();
        while (!firstName().isDisplayed() && System.currentTimeMillis() - timer < 10000);
        Wait wait = new Wait();
        wait.waitMilSec(500);
        
        username().sendKeys(username);
        firstName().sendKeys(firstName);
        lastName().sendKeys(lastName);
        title().sendKeys(title);
        role().sendKeys(role);
        manager().sendKeys(manager);
        status().sendKeys(status);
        bridgeTime().sendKeys(bridgeTime);
        location().sendKeys(location);
        startDate().sendKeys(startDate);
        cellPhone().sendKeys(cellPhone);
        officePhone().sendKeys(officePhone);
        email().sendKeys(email);
        imName().sendKeys(imName);
        imClient().sendKeys(imClient);
        department().sendKeys(department);
        wait.waitMilSec(500);
        btnCreateEmployee().click();
    }
    
}
