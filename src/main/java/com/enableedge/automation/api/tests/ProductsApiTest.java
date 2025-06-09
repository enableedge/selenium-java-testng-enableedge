package com.enableedge.automation.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductsApiTest {
    private static final String BASE_URL = "https://www.automationexercise.com/api";

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(priority = 1)
    public void testGetProducts() {
        Response response = given()
                .when()
                .get("/products")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.body().jsonPath().getList("data").size() > 0, "Products list should not be empty");
    }

    @Test(priority = 2)
    public void testGetProductById() {
        String productId = "1";
        Response response = given()
                .pathParam("id", productId)
                .when()
                .get("/product/{id}")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertNotNull(response.body().jsonPath().get("data"), "Product data should not be null");
    }

    @Test(priority = 3)
    public void testGetProductsByCategory() {
        String category = "Men";
        Response response = given()
                .queryParam("category", category)
                .when()
                .get("/products")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.body().jsonPath().getList("data").size() > 0, "Products list should not be empty");
    }

    @Test(priority = 4)
    public void testGetNonExistentProduct() {
        String productId = "99999";
        Response response = given()
                .pathParam("id", productId)
                .when()
                .get("/product/{id}")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 404, "Status code should be 404");
    }
}
