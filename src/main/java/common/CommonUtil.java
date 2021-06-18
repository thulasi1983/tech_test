package common;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public final class CommonUtil {
    private static final Logger logger = Logger.getLogger(CommonUtil.class);
    public static WebDriver driver;
    static Properties localProperties = null;

    public  Properties getLocalProperties()  throws FileNotFoundException{
        String str = System.getProperty(constants.USER_DIRECTORY) + File.separator + "src" + File.separator
                + "main" + File.separator + "resources" + File.separator + "config.properties";
        InputStream localInputStream = new FileInputStream(str);

        if (localInputStream != null) {
            localProperties = new Properties();
            try {
                localProperties.load(localInputStream);
            } catch (Exception e) {
                if (logger.isInfoEnabled()) {
                    logger.info("config properties not loaded" + e);
                }
            }
        }

        return localProperties;
    }

    // opening the browser
    public  WebDriver getDriver(String browserType) {

        if (logger.isInfoEnabled()) {
            logger.info("Opening browser in " + browserType);
        }

        if (browserType.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    System.getProperty(constants.USER_DIRECTORY) + "//geckoDriver.exe");
            driver=new FirefoxDriver();

        }
        else if (browserType.equalsIgnoreCase("Chrome")) {

                System.setProperty("webdriver.chrome.driver",
                        System.getProperty("user.dir") + "//chromedriver");

            driver=new ChromeDriver();


        } else {
            logger.info("Browser not defined");
        }
       return driver;
    }

    public void loadURL(WebDriver driver,String URL) {
        if (logger.isInfoEnabled()) {
            logger.info("Inside loadURL method " + URL);
        }
        driver.navigate().to(URL);
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);
        WebDriverWait wait = getWebDriverWaitInstance();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='Amazon']")));

    }
    public  WebDriverWait getWebDriverWaitInstance() {
        return new WebDriverWait(driver, Integer.parseInt(localProperties.getProperty("timeOut")));
    }
    public void clickOnLogo() {

        WebElement we=driver.findElement(By.xpath("//a[@aria-label='Amazon']"));

            we.click();



        }


}
