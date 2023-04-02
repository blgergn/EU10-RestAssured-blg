package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {

    String url = "http://18.234.192.234:8000";


   //    Given Accept type application/json
   //    When user send GET request to api/spartans end point
   //    Then status code must 200
   //    And response Content Type must be application/json
   //    And response body should include spartan result


      @Test
      public void test1(){

          Response response = RestAssured.given().accept(ContentType.JSON)
                  .when()
                  .get(url + "/api/spartans");

          //printing status code from response object
          System.out.println("response.statusCode() = " + response.statusCode());

          //printing response content type from response object
          System.out.println("response.contentType() = " + response.contentType());

          //print whole result body

          response.prettyPrint();

          //how to do API testing then ?

          //verify status code is 200
          Assertions.assertEquals(response.statusCode(),200);

          //verify content type is application/json
          Assertions.assertEquals(response.contentType(),"application/json");


      }




}