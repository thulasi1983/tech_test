package com.testng.tests;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APIValidation {
    @Test
    public void verifyAPI(){
        Response response;
        RestAssured.baseURI="https://www.amazon.com/";
        response=given().given().when().log().all().get().then().extract().response();
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertTrue(response.getStatusLine().contains("HTTP/1.1 200"));
    }
}
