package pageObjects;

import common.CommonUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
    private String alertMessageXpath="//div[contains(@id,'alertName-missing-alert')]//div[contains(@class,'alert-content')]";
    private By logoOnRegisterPage=By.xpath("//i[@role='img' and @aria-label='Amazon']");
    private By submitButton=By.id("continue");
    private WebDriver driver;
    private WebDriverWait wait;

    public RegisterPage(WebDriver driver){
        CommonUtil commonUtil=new CommonUtil();
        this.driver=driver;
        wait=commonUtil.getWebDriverWaitInstance();
    }
    public void waitForRegisterPageToLoad(){
        wait.until(ExpectedConditions.elementToBeClickable(logoOnRegisterPage));
    }

    public String getAlertMessage(String missingInput){
          WebElement we=driver.findElement(By.xpath(alertMessageXpath.replaceAll("alertName",missingInput)));
          return we.getText();
    }
    public void clickOnCreateButton(){
        driver.findElement(submitButton).click();
    }
}
