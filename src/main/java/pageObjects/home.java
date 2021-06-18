package pageObjects;

import common.CommonUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.*;

public class home {
    private By searchBox=By.xpath("//input[contains(@id,'searchtextbox')]");
    private By accountAndLists=By.xpath("//span[contains(@id,'accountList')]");
    private By account=By.xpath("//a[contains(@href,'Account')]/span[contains(text(),'Account')]");
    private By startHere=By.xpath("//a[contains(text(),'Start here')]");

    WebDriver driver;
    CommonUtil commonUtil;
    public home(WebDriver driver){
        this.driver=driver;
        commonUtil=new CommonUtil();
    }

    public void mouseOverAccountsAndLists(){
        Actions action=new Actions(driver);
        action.moveToElement(driver.findElement(accountAndLists)).build().perform();
    }
    public void clickOnAccounts(){
        driver.findElement(account).click();
        commonUtil.getWebDriverWaitInstance().until(ExpectedConditions.urlContains("nav_AccountFlyout_ya"));
    }
    public void performSearch(String searchItem){
        WebElement srchBox=driver.findElement(searchBox);
        Actions actions=new Actions(driver);
        actions.sendKeys(srchBox,searchItem).build().perform();
        srchBox.sendKeys(Keys.ENTER);

    }
    public void clickOnStartHere(){
        WebElement we=driver.findElement(startHere);
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].click()", we);

    }
}
