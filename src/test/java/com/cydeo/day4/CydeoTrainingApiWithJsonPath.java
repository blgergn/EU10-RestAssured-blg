package com.cydeo.day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

public class CydeoTrainingApiWithJsonPath {

    @BeforeAll
    public static void init() {
        baseURI = "https://api.training.cydeo.com";
    }


    @DisplayName("GET request to individual student")
    @Test
    public void test1(){
        //send a get request to student id 2 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8
        //verify Date header exists
        //assert that
            /*
                firstName Mark
                batch 13
                companyName Cydeo
                state Virginia
             */

        Response response = given().accept(ContentType.JSON).pathParam("id", 2)
                .when().get(baseURI + "/student/{id}");

            assertEquals(200,response.statusCode());
            assertEquals("application/json;charset=UTF-8", response.contentType());
            assertTrue(response.headers().hasHeaderWithName("date"));

            JsonPath jsonPath=response.jsonPath();

        String firstName = jsonPath.getString("students[0].firstName");
        System.out.println("firstName = " + firstName);
        int batchNumber = jsonPath.getInt("students[0].batch");
        System.out.println("batchNumber = " + batchNumber);
        String companyName = jsonPath.getString("students[0].company.companyName");
        System.out.println("companyName = " + companyName);
        String state = jsonPath.getString("students[0].company.address.state");
        System.out.println("state = " + state);

        assertEquals("Mark", firstName);
        assertEquals(13, batchNumber);
        assertEquals("Cydeo", companyName);
        assertEquals("Virginia", state);


    }
}
