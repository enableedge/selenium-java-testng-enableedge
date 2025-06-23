package com.enableedge.automation.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OrdersApiTest {
    private static final String BASE_URL = "https://www.automationexercise.com/api";

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(priority = 1)
    public void testGetAllOrders() {
        Response response = given()
                .when()
                .get("/orders")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.body().jsonPath().getList("data").size() > 0, "Orders list should not be empty");
    }

    @Test(priority = 2)
    public void testGetOrderById() {
        String orderId = "1";
        Response response = given()
                .pathParam("id", orderId)
                .when()
                .get("/order/{id}")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertNotNull(response.body().jsonPath().get("data"), "Order data should not be null");
    }

    @Test(priority = 3)
    public void testCreateOrder() {
        Response response = given()
                .contentType("application/json")
                .body("{\"userId\": 1, \"products\": [{\"id\": 1, \"quantity\": 2}]}")
                .when()
                .post("/order")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 201, "Status code should be 201");
        Assert.assertNotNull(response.body().jsonPath().get("data.id"), "Order ID should be present");
    }

    @Test(priority = 4)
    public void testUpdateOrder() {
        String orderId = "1";
        Response response = given()
                .pathParam("id", orderId)
                .contentType("application/json")
                .body("{\"status\": \"completed\"}")
                .when()
                .put("/order/{id}")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 5)
    public void testDeleteOrder() {
        String orderId = "1";
        Response response = given()
                .pathParam("id", orderId)
                .when()
                .delete("/order/{id}")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
    }
}
