package com.johndoll.bluesourceselenium.tests;

import com.johndoll.bluesourceselenium.pages.DepartmentsPage;
import com.johndoll.bluesourceselenium.pages.Links;
import com.johndoll.bluesourceselenium.pages.LoginPage;
import com.johndoll.bluesourceselenium.utility.ExcelReader;
import com.johndoll.bluesourceselenium.utility.ResourceLocation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.*;

/**
 * @author Jonathan Doll
 */
public class Departments {
    
    private static WebDriver driver;
    private static Links link;
    private static DepartmentsPage dept;
    private long timer;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        driver = new FirefoxDriver();
        driver.get("http://bluesourcestaging.herokuapp.com/");
        LoginPage login = new LoginPage(driver);
        login.login("company.admin", "The McRib is back");
        link = new Links(driver);
        dept = new DepartmentsPage(driver);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        link.logout().click();
        driver.close();
    }
    
    @DataProvider
    public Object[][] addDeptData(){
        return new ExcelReader(ResourceLocation.TestDataLocation + "AddDept.xlsx").worksheetToArray(1);
    }
    
    @Test(dataProvider = "addDeptData", groups = "add", threadPoolSize = 3)
    public void addDept(String deptName, String parentDeptName){
        link.admin().click();
        link.departments().click();
        
        dept.addDept(deptName, parentDeptName);
        
        timer = System.currentTimeMillis();
        while(!dept.createSuccessful() && !dept.createFailure() && System.currentTimeMillis() - timer < ResourceLocation.PageWaitTime);
        
        try{
            assertTrue(dept.createSuccessful(), "The department was successfully created");
            assertTrue(dept.departmentExists(deptName), "The department was successfully found in the list");
        }catch(AssertionError e){
            System.err.println(e);
            assertTrue(false);
        }
    }
    
    @DataProvider
    public Object[][] addSubdeptData(){
        return new ExcelReader(ResourceLocation.TestDataLocation + "AddSubdept.xlsx").worksheetToArray(1);
    }
    
    @Test(dataProvider = "addSubdeptData", groups = "addsub", dependsOnGroups = "add", threadPoolSize = 3)
    public void addSubdept(String deptName, String subdeptName, String parentDeptName){
        link.admin().click();
        link.departments().click();
        
        dept.addSubdept(deptName, subdeptName, parentDeptName);
        
        timer = System.currentTimeMillis();
        while(!dept.createSuccessful() && !dept.createFailure() && System.currentTimeMillis() - timer < ResourceLocation.PageWaitTime);
        
        try{
            assertTrue(dept.createSuccessful(), "The subdepartment was successfully created");
            assertTrue(dept.departmentExists(subdeptName), "The subdepartment was successfully found in the list");
        }catch(AssertionError e){
            System.err.println(e);
            assertTrue(false);
        }
    }
    
    @DataProvider
    public Object[][] editDeptData(){
        return new ExcelReader(ResourceLocation.TestDataLocation + "EditDept.xlsx").worksheetToArray(1);
    }
    
    @Test(dataProvider = "editDeptData", groups = "edit", dependsOnGroups = "addsub", threadPoolSize = 3)
    public void editDept(String deptName, String newDeptName, String parentDeptName){
        link.admin().click();
        link.departments().click();
        
        dept.editDept(deptName, newDeptName, parentDeptName);
        
        timer = System.currentTimeMillis();
        while(!dept.createSuccessful() && !dept.createFailure() && System.currentTimeMillis() - timer < ResourceLocation.PageWaitTime);
        
        try{
            assertTrue(dept.createSuccessful(), "The department was successfully edited");
            assertTrue(dept.departmentExists(newDeptName), "The department was successfully found in the list");
        }catch(AssertionError e){
            System.err.println(e);
            assertTrue(false);
        }
    }
    
    @DataProvider
    public Object[][] deleteDeptData(){
        return new ExcelReader(ResourceLocation.TestDataLocation + "DeleteDept.xlsx").worksheetToArray(1);
    }
    
    @Test(dataProvider = "deleteDeptData", dependsOnGroups = "edit", threadPoolSize = 3)
    public void deleteDept(String deptName){
        link.admin().click();
        link.departments().click();
        
        dept.deleteDept(deptName);
        
        timer = System.currentTimeMillis();
        while(!dept.createSuccessful() && !dept.createFailure() && System.currentTimeMillis() - timer < ResourceLocation.PageWaitTime);
        
        try{
            assertTrue(dept.createSuccessful(), "The department was successfully deleted");
            assertTrue(!dept.departmentExists(deptName), "The department was successfully removed from the list");
        }catch(AssertionError e){
            System.err.println(e);
            assertTrue(false);
        }
    }
}
