package com.enableedge.automation.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UsersApiTest {
    private static final String BASE_URL = "https://www.automationexercise.com/api";

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(priority = 1)
    public void testGetAllUsers() {
        Response response = given()
                .when()
                .get("/users")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.body().jsonPath().getList("data").size() > 0, "Users list should not be empty");
    }

    @Test(priority = 2)
    public void testGetUserById() {
        String userId = "1";
        Response response = given()
                .pathParam("id", userId)
                .when()
                .get("/user/{id}")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertNotNull(response.body().jsonPath().get("data"), "User data should not be null");
    }

    @Test(priority = 3)
    public void testCreateUser() {
        Response response = given()
                .contentType("application/json")
                .body("{\"name\": \"John Doe\", \"email\": \"test" + System.currentTimeMillis() + "@example.com\"}")
                .when()
                .post("/user")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 201, "Status code should be 201");
        Assert.assertNotNull(response.body().jsonPath().get("data.id"), "User ID should be present");
    }

    @Test(priority = 4)
    public void testUpdateUser() {
        String userId = "1";
        Response response = given()
                .pathParam("id", userId)
                .contentType("application/json")
                .body("{\"name\": \"Updated Name\"}")
                .when()
                .put("/user/{id}")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 5)
    public void testDeleteUser() {
        String userId = "1";
        Response response = given()
                .pathParam("id", userId)
                .when()
                .delete("/user/{id}")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
    }
}
