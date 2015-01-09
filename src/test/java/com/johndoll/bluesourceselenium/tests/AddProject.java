package com.johndoll.bluesourceselenium.tests;

import com.johndoll.bluesourceselenium.pages.Links;
import com.johndoll.bluesourceselenium.pages.LoginPage;
import com.johndoll.bluesourceselenium.pages.ProjectPage;
import com.johndoll.bluesourceselenium.utility.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.johndoll.bluesourceselenium.utility.ResourceLocation;

/**
 * @author Jonathan Doll
 */
public class AddProject {
    
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
    public Object[][] addProjectData(){
        return new ExcelReader(ResourceLocation.TestDataLocation + "AddProject.xlsx").worksheetToArray(1);
    }
    
    @Test(dataProvider = "addProjectData",  threadPoolSize = 3)
    public void addProject(String projectName, String clientPartner, String teamLead, String status, String startDate, String endDate){
        Links links = new Links(driver);
        links.projects().click();

        ProjectPage project = new ProjectPage(driver);
        project.createProject(projectName, clientPartner, teamLead, status, startDate, endDate);
        
        long timer = System.currentTimeMillis();
        while (!project.createFailure() && !project.createSuccessful() && System.currentTimeMillis() - timer < 10000);
        
        try{
            assertTrue(project.createSuccessful(), "Project was created successfully");
            assertTrue(project.projectSearch(projectName), "Project was found in the list");
        }catch(AssertionError e){
            System.err.println(e);
            assertTrue(false);
        }
    }
    
}
