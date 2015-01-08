package com.johndoll.bluesourceselenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Jonathan Doll
 */
public class DepartmentsPage {
    private WebDriver driver;
    
    public DepartmentsPage(WebDriver driver){
        this.driver = driver;
    }
    
    public WebElement editDepartment(String departmentName){
        return driver.findElement(By.xpath("//li[contains(text(), '" + departmentName + "')]/div/a/span[@class='glyphicon glyphicon-pencil']"));
    }
    
    public WebElement deleteDepartment(String departmentName){
        return driver.findElement(By.xpath("//li[contains(text(), '" + departmentName + "')]/div/a/span[@class='glyphicon glyphicon-trash']"));
    }
    
    public WebElement addDepartment(){
        return driver.findElement(By.linkText("Add Department"));
    }
    
    public WebElement addSubdepartment(String departmentName){
        return driver.findElement(By.xpath("//li[contains(text(), '" + departmentName + "')]/a[contains(text(), 'Add Subdepartment')]"));
    }
    
    public boolean departmentExists(String departmentName){
        return driver.findElements(By.xpath("//li[contains(text(), '" + departmentName + "')]")).size() > 0;
    }
    
    public boolean createSuccessful(){
        return driver.findElements(By.className("alert-success")).size() > 0;
    }
    
    public boolean createFailure(){
        return driver.findElements(By.className("alert-danger")).size() > 0;
    }
    
}
