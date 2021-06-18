package pageObjects;

import common.CommonUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResultsPage {
    private String resultsInfoBarXpath="//span[contains(@class,'RESULT_INFO_BAR')]//span[contains(text(),'searchItem')]";
    private String starRatingXpath="//section[contains(@aria-label,'Stars')]//span[contains(text(),'Number Stars & Up')]";
    private By searchResultItems=By.xpath("//span[contains(@aria-label,'star')]//parent::div/parent::div/preceding-sibling::div//span[contains(@class,'text-normal')]");
    private WebDriver driver;
    private WebDriverWait wait;
    //div[contains(@class,'result')]//a[contains(@class,'link-normal')]
    public SearchResultsPage(WebDriver driver){
        CommonUtil commonUtil=new CommonUtil();
        this.driver=driver;
        wait=commonUtil.getWebDriverWaitInstance();
    }
    public void waitForSearchResultsLoaded(String searchText){
        WebElement results=driver.findElement(By.xpath(resultsInfoBarXpath.replaceAll("searchItem",searchText)));
       wait.until(ExpectedConditions.visibilityOf(results));

    }
    public void clickOnCustomerReviewRating(String s){
        WebElement we= driver.findElement(By.xpath(starRatingXpath.replaceAll("Number",s)));
       JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", we);

    }

    public String getSpecificItemFromSearchResults(int requiredItemNumber){
        String searchItemText="";
        List<WebElement> searchResults=driver.findElements(searchResultItems);
        for(int i=0;i<searchResults.size();i++){
            if(i==requiredItemNumber-1){
                searchItemText=searchResults.get(i).getText();
                break;
            }
        }
        return searchItemText;
    }
}
