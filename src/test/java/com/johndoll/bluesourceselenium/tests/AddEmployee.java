package com.johndoll.bluesourceselenium.tests;

import com.johndoll.bluesourceselenium.pages.EmployeePage;
import com.johndoll.bluesourceselenium.pages.Links;
import com.johndoll.bluesourceselenium.pages.LoginPage;
import com.johndoll.bluesourceselenium.utility.ExcelReader;
import com.johndoll.bluesourceselenium.utility.ResourceLocation;
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
public class AddEmployee {
    
    private static WebDriver driver;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        driver = new FirefoxDriver();
        driver.get("http://bluesourcestaging.herokuapp.com/");
        LoginPage login = new LoginPage(driver);
        login.login("company.admin", "The McRib is back");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        driver.close();
    }
    
    @DataProvider
    public Object[][] addEmployeeData(){
        return new ExcelReader(ResourceLocation.TestDataLocation + "AddEmployee.xlsx").worksheetToArray(1);
    }
    
    @Test(dataProvider = "addEmployeeData",  threadPoolSize = 3)
    public void addEmployee(String username, String firstName, String lastName, String title, String role, String manager, String status, String bridgeTime, String location, String startDate, String cellPhone, String officePhone, String email, String imName, String imClient, String department){
        Links link = new Links(driver);
        link.employees().click();
        
        EmployeePage employee = new EmployeePage(driver);
        employee.createNewEmployee(username, firstName, lastName, title, role, manager, status, bridgeTime, location, startDate, cellPhone, officePhone, email, imName, imClient, department);
        
        long timer = System.currentTimeMillis();
        while (!employee.createFailure() && !employee.createSuccessful() && System.currentTimeMillis() - timer < 10000);
        
        try{
            assertTrue(employee.createSuccessful(), "Employee Created Successfully");
            assertTrue(employee.employeeSearch(firstName, lastName), "Employee successfully found in list");
        }catch(AssertionError e){
            System.err.println(e);
            assertTrue(false);
        }
    }
    
}
