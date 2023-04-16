package com.cydeo.day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import com.cydeo.utilities.HRTestBase;

public class ORDSApiWithJsonPath extends HRTestBase {

    @DisplayName("GET request to Countries")
    @Test
    public void test1(){

        Response response = get("/countries");


        //get the second country name with JsonPath

        //to use JsonPath we assign response to JsonPath

        JsonPath jsonPath=response.jsonPath();

        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        List<String> allCountryIds = jsonPath.getList("items.country_id");
        System.out.println("allCountryIds = " + allCountryIds);

        //get all country names where their region id is equal to 2
        List<String> countryNameWithRegionId2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("countryNameWithRegionId2 = " + countryNameWithRegionId2);


        //it=each
        //{it.region_id==2}=if condition
        //country_name=value

    }

    @DisplayName("GET request /employees with query param")
    @Test
    public void test2(){
        
        //we added limit query param to get 107 employees
        Response res = given().queryParam("limit", 107).get("/employees");

        //get me all email of emails of employees who is working as IT_PROG
        JsonPath jsonPath=res.jsonPath();

        List<String> emailWhoWorkingAsITPROG = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println("emailWhoWorkingasITPROG = " + emailWhoWorkingAsITPROG);

        //get me first name of employees who is making more than 10000
        List<String> firstNameWithSalary = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("firstNameWithSalary = " + firstNameWithSalary);
        
        //get the max salary first_name
        String kingFirstName=jsonPath.getString("items.max {it.salary}.first_name");
        String kingNameWithPathMethod=res.path("items.max {it.salary}.first_name");
        System.out.println("kingFirstName = " + kingFirstName);
        System.out.println("kingNameWithPathMethod = " + kingNameWithPathMethod);


    }
}
