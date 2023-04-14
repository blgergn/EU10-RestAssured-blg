package com.cydeo.day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(200,response.statusCode());

        //print limit result

        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print has more

        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print first CountryId
        String firstCountryId=response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        //print second country name
        String secondCountryName=response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //print "http://18.234.192.234:1000/ords/hr/countries/CA"
        String specificHref =response.path("items[4].links[0].href");
        System.out.println("specificHref = " + specificHref);

        //get me all country names
        List<String> countryNames=response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);
        
        //assert that all regions ids are equal to 2
        List<Integer> allregionsID2=response.path("items.region_id");
        for (Integer regionsID : allregionsID2){
            System.out.println("regionsID = " + regionsID);
            assertEquals(2,regionsID);
        }

    }

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

        List<String> allJobIDs=res.path("items.job_id");
        
        for(String jobID :allJobIDs){
            System.out.println("jobID = " + jobID);
            assertEquals("IT_PROG",jobID);
        }


    }



}
