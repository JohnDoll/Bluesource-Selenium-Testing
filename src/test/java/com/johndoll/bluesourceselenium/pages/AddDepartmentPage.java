package com.johndoll.bluesourceselenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Jonathan Doll
 */
public class AddDepartmentPage {

    private WebDriver driver;

    public AddDepartmentPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement departmentName() {
        return driver.findElement(By.xpath("//input[@id='department_name']"));
    }

    public WebElement parentDepartmentName() {
        return driver.findElement(By.xpath("//select[@id='department_department_id']"));
    }

    public WebElement btnCreateDepartment() {
        return driver.findElement(By.xpath("//input[@value='Create Department']"));
    }

}
