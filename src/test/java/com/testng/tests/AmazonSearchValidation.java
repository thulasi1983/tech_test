package com.testng.tests;

import common.CommonUtil;
import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.AccountPage;
import pageObjects.RegisterPage;
import pageObjects.SearchResultsPage;
import pageObjects.home;

import javax.xml.ws.Response;
import java.io.FileNotFoundException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class AmazonSearchValidation {
    WebDriver driver;
    CommonUtil commonUtil;
    Properties props;
    home homePage;
    AccountPage accountPage;
    SearchResultsPage searchResultsPage;
    RegisterPage registerPage;

    @BeforeTest
    public void setUp(){
        commonUtil=new CommonUtil();
        try{
            props=commonUtil.getLocalProperties();
        }
        catch (FileNotFoundException fe){
            fe.printStackTrace();
        }
    }
    @BeforeMethod(groups = {"beforeMethod"})
    public void launchAmazonUrl(){
        CommonUtil commonUtil=new CommonUtil();
        String browser=props.getProperty("webBrowser");
        String url=props.getProperty("url");
        driver=commonUtil.getDriver(browser);
        commonUtil.loadURL(driver,url);

    }
    @Test(groups = { "myGroup" })
    public void verifyAccountPage(){
        homePage=new home(driver);
        homePage.mouseOverAccountsAndLists();
        homePage.clickOnAccounts();
        accountPage=new AccountPage(driver);
            Assert.assertTrue(accountPage.isCardTextDisplayed("Your Orders"));
            Assert.assertTrue(accountPage.isCardTextDisplayed("Login & security"));
//        System.out.println("Completed");

    }
    @Test(groups = { "myGroup"})
    public void get4StarRatedItem(){
        homePage=new home(driver);
        homePage.performSearch("Apple Laptop");
        searchResultsPage=new SearchResultsPage(driver);
        searchResultsPage.waitForSearchResultsLoaded("Apple Laptop");
        searchResultsPage.clickOnCustomerReviewRating("4");
        System.out.println(searchResultsPage.getSpecificItemFromSearchResults(1));

    }
    @Test
    public void verifyAccountErrorMessages(){
        homePage=new home(driver);
        homePage.mouseOverAccountsAndLists();

        homePage.clickOnStartHere();
        registerPage=new RegisterPage(driver);
        registerPage.waitForRegisterPageToLoad();
        registerPage.clickOnCreateButton();
        Assert.assertEquals(registerPage.getAlertMessage("Name"),"Enter your name");
        driver.navigate().to("https://www.amazon.com/");
        
    }


    @AfterMethod(groups = { "myGroup" })
    public void backToHomePage(){
        commonUtil.clickOnLogo();
        driver.quit();
    }


}
