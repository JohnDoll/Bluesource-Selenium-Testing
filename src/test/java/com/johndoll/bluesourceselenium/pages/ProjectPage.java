package bluesourcepages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Jonathan Doll
 */
public class ProjectPage {
    
    private WebDriver driver;
    
    public ProjectPage(WebDriver driver){
        this.driver = driver;
    }
    
    public WebElement btnAdd(){
        return driver.findElement(By.xpath("//button[contains(text(),'Add')]"));
    }
    
    public WebElement showInactives(){
        return driver.findElement(By.xpath(("//button[contains(text(), 'Show Inactives')]")));
    }
    
    public WebElement name(){
        return driver.findElement(By.id("project_name"));
    }
    
    public WebElement clientPartner(){
        return driver.findElement(By.id("project_client_partner_id"));
    }
    
    public WebElement btnAddTeamLead(){
        return driver.findElement(By.id("add-team-lead"));
    }
    
    public WebElement teamLeads(){
        return driver.findElement(By.id("project_leads"));
    }
    
    public WebElement status(){
        return driver.findElement(By.id("project_status"));
    }
    
    public WebElement startDate(){
        return driver.findElement(By.id("project_start_date"));
    }
    
    public WebElement endDate(){
        return driver.findElement(By.id("project_end_date"));
    }
    
    public WebElement btnCreateProject(){
        return driver.findElement(By.xpath("//input[@value='Create Project']"));
    }
    
    public WebElement btnClose(){
        return driver.findElement(By.xpath(("//button[contains(text(), 'Close')]")));
    }
    
    public boolean addFormExists(){
        return name().isDisplayed();
    }
    
    public boolean createSuccessful(){
        return driver.findElements(By.className("alert-success")).size() > 0;
    }
    
    public boolean createFailure(){
        return driver.findElements(By.className("alert-danger")).size() > 0;
    }
    
    public List<WebElement> allTeamLeads(){
        return driver.findElements(By.id("project_leads"));
    }
    
    public WebElement searchBar(){
        return driver.findElement(By.xpath("//input[@id='search-bar']"));
    }
    
    public boolean searchProjectExists(String project){
        return driver.findElements(By.linkText(project)).size() > 0;
    }
    
    public boolean nextPageExists(){
        return driver.findElements(By.linkText("»")).size() > 0;
    }
    
    public boolean previousPageExists(){
        return driver.findElements(By.linkText("«")).size() > 0;
    }
    
    public WebElement nextPage(){
        return driver.findElement(By.linkText("»"));
    }
    
    public WebElement previousPage(){
        return driver.findElement(By.linkText("«"));
    }
}
