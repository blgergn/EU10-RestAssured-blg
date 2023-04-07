package com.cydeo.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestsWithParameters {

    @BeforeAll
    public static void init() {
        baseURI = "http://18.234.192.234:1000/ords/hr";
    }

      /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */

    @DisplayName("GET request to /countries with Query Param")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON).queryParam("q", "{\"region_id\":2}")
                .log().all().when().get("/countries");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json", response.header("Content-Type"));
        Assertions.assertTrue(response.asString().contains("United States of America"));

        response.prettyPrint();
    }



    /*
        Send a GET request to employees and get only employees who works as a IT_PROG
     */

    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2() {

        Response res = given().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .log().all().when().get("/employees");


        assertEquals(200,res.statusCode());
        assertEquals("application/json",res.header("Content-Type"));
        assertTrue(res.body().asString().contains("IT_PROG"));

        //i need to verify only job_id is IT_prog, so I go inside the json. navigate inside json and get the data.
        //asString().contains() is not proper test, because it check all string, not the specific part


        res.prettyPrint();



    }

}