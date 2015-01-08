package com.johndoll.bluesourceselenium.tests;

import bluesourcepages.EmployeePage;
import bluesourcepages.TimeOffPage;
import com.johndoll.bluesource.selenium.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Jonathan Doll
 */
public class Login {
    private static WebDriver driver;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        driver = new FirefoxDriver();
        driver.get("http://bluesourcestaging.herokuapp.com/");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        driver.close();
    }
    
    @DataProvider
    public Object[][] loginData(){
        Object[][] test = new Object[1][1];
        return test;
    }
    
    @Test(dataProvider = "loginData")
    public void login(String username, String password){
        LoginPage login = new LoginPage(driver);
        login.login(username, password);
        
        EmployeePage employee = new EmployeePage(driver);
        TimeOffPage timeOff = new TimeOffPage(driver);
        if(employee.welcomeMessage()){
        assertTrue(employee.welcomeMessage(), "Welcome Message Displayed.");
        }else if(timeOff.timeOffMessage()){
            assertTrue(timeOff.timeOffMessage(), "Time Off Message Displayed");
        } 
    }
    
}
