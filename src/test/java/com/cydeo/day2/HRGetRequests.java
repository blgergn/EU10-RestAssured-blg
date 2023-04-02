package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HRGetRequests {

    //BeforeAll is an annotation equals to @BeforeClass in testNg, we use with static method name
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://18.234.192.234:1000/ords/hr";
    }


    @DisplayName("GET request to /regions")
    @Test
    public void test1() {

        Response response = RestAssured.get("/regions"); //only the end points

        System.out.println(response.statusCode());


    }


      /*
        Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains   Americas
     */

    @DisplayName("GET request to /regions/2")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when()
                        .get("/regions/2");

        //verify status code
        assertEquals(200, response.statusCode());
        //verify content type
        assertEquals("application/json",response.contentType());

        //verify body contains Americas
        response.prettyPrint();
        assertTrue(response.body().asString().contains("Americas"));


    }









}
