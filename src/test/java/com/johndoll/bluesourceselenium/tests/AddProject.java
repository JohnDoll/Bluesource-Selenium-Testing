package com.johndoll.bluesourceselenium.tests;

import com.johndoll.bluesourceselenium.utility.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Jonathan Doll
 */
public class AddProject {
    
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
    public Object[][] addProjectData(){
        ExcelReader addProject = new ExcelReader("src\\test\\resources\\AddProject.xlsx");
        return addProject.worksheetToArray(1);
    }
    
    @Test(dataProvider = "addProjectData",  threadPoolSize = 3)
    public void addProject(){
        
    }
    
}
