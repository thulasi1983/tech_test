package pageObjects;

import common.CommonUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {
    private By pageHeader=By.xpath("//h1[contains(text(),'Your Account')]");
    private String cardTextXpath="//h2[contains(@class,'card__heading') and contains(text(),'cardText')]";
    private WebDriver driver;
    private WebDriverWait wait;

    public AccountPage(WebDriver driver){
        CommonUtil commonUtil=new CommonUtil();
        this.driver=driver;
        wait=commonUtil.getWebDriverWaitInstance();
    }
    public boolean isPageHeaderDisplayed(){
        return driver.findElement(pageHeader).isDisplayed();
    }
    public boolean isCardTextDisplayed(String cText){

        return driver.findElement(By.xpath(cardTextXpath.replaceAll("cardText",cText))).isDisplayed();
    }

}
