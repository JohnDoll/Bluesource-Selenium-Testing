package com.johndoll.bluesourceselenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Jonathan Doll
 */
public class TitlePage {

    private WebDriver driver;
    private NewTitlePage nTitle;
    private EditTitlePage eTitle;

    public TitlePage(WebDriver driver) {
        this.driver = driver;
        this.nTitle = new NewTitlePage(this.driver);
        this.eTitle = new EditTitlePage(this.driver);
    }

    public WebElement deleteTitle(String title) {
        return driver.findElement(By.xpath("//td[contains(text(), '" + title + "')]//div//a//span[@class='glyphicon glyphicon-trash']"));
    }

    public WebElement editTitle(String title) {
        return driver.findElement(By.xpath("//td[contains(text(), '" + title + "')]//div//a//span[@class='glyphicon glyphicon-pencil']"));
    }

    public WebElement addTitle() {
        return driver.findElement(By.linkText("New Title"));
    }
    
    public WebElement newTitleName(){
        return nTitle.name();
    }
    
    public WebElement btnCreateTitle(){
        return nTitle.btnCreateTitle();
    }
    
    public WebElement editTitleName(){
        return eTitle.name();
    }
    
    public WebElement btnUpdateTitle(){
        return eTitle.btnUpdateTitle();
    }
    
    public boolean createSuccessful(){
        return driver.findElements(By.className("alert-success")).size() > 0;
    }
    
    public boolean createFailure(){
        return driver.findElements(By.className("alert-danger")).size() > 0;
    }
    
    public boolean titleExists(String title){
        return driver.findElements(By.xpath("//td[contains(text(), '" + title + "')]//div//a//span[@class='glyphicon glyphicon-pencil']")).size() > 0;
    }
    
}
