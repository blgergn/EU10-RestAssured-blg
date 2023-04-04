package com.cydeo.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithParameters {

    @BeforeAll
    public static void init() {
        baseURI = "http://18.234.192.234:8000";
    }

     /*   Given accept type is Json
          And Id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "Blythe" should be in response payload(body)
       */

    @DisplayName("GET request to /api/spartans/{id} with ID 5")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON).and().pathParam("id", 5)
                .when().get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Blythe"));

    }


      /*

        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

    @DisplayName("GET request /api/spartans/{id} Negative Test")
    @Test
    public void test2() {

        Response response2 = given().accept(ContentType.JSON).pathParam("id", 500)
                .when().get("/api/spartans/{id}");

        assertEquals(404, response2.statusCode());
        assertEquals("application/json", response2.contentType());
        assertTrue(response2.body().asString().contains("Not Found"));


    }


    /*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @DisplayName("GET request to /api/spartans/search with Query Params")
    @Test
    public void test3() {
        Response response3 = given().accept(ContentType.JSON).and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "e")
                .when().get("/api/spartans/search");

        //.log().all()  when we add them between given and accept, it is showing all the parts,all the product in console.
        //it is optional to see your request. not mandatory
        //also you can add different parts. log().parameters() also to see all details the request parameters


        assertEquals(200, response3.statusCode());
        assertEquals("application/json", response3.contentType());
        assertTrue(response3.body().asString().contains("Female"));
        assertTrue(response3.body().asString().contains("Janette"));

    }

    @DisplayName("GET request to /api/spartans/search with Query Params (MAP)")
    @Test
    public void test4() {
        //create a map and add query parameters
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("nameContains","e");
        queryMap.put("gender","Female");

        Response response = given().
                log().all()
                .accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when()
                .get("/api/spartans/search");

        //ya response içine queryParam ile key+value ekleyebilirsin.
        // ya da map açıp ona key+value ekleyerek,
        // o map i response içinde queryParams ile ekleyebilirsin. """S var dikkat!"""

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));




}

}