package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {

    String baseurl = "http://18.234.192.234:8000";


    //    Given Accept type application/json
    //    When user send GET request to api/spartans end point
    //    Then status code must 200
    //    And response Content Type must be application/json
    //    And response body should include spartan result


    @Test
    public void test1() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseurl + "/api/spartans");

        //printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        System.out.println("response.contentType() = " + response.contentType());

        //print whole result body

        response.prettyPrint();

        //how to do API testing then ?

        //verify status code is 200
        Assertions.assertEquals(response.statusCode(), 200);

        //verify content type is application/json
        Assertions.assertEquals(response.contentType(), "application/json");


    }

        /*
        Given accept header is application/json
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json
        and json body should contain Fidole
     */

    @DisplayName("GET one spartan/api/spartan/3 and verify")
    @Test
    public void test2() {

        Response response2 = RestAssured.given().accept("application/json")
                .when()
                .get(baseurl + "/api/spartans/3");


        //verify status code 200
        Assertions.assertEquals(response2.statusCode(), 200);

        //verify content type
        Assertions.assertEquals(response2.contentType(), "application/json");

        //verify json body contains Fidole
        Assertions.assertTrue(response2.body().asString().contains("Fidole"));


    }

         /*
        Given no headers provided
        When Users sends GET request to /api/hello
        Then response status code should be 200
        And Content type header should be “text/plain;charset=UTF-8”
        And header should contain date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
        */

    @DisplayName("GET request to /api/hello")
    @Test
    public void test3() {

        //header olmadığı için given yok
        Response response3 = RestAssured
                .when()
                .get(baseurl + "/api/hello");

        //send request and save response inside the response object
        Assertions.assertEquals(200, response3.statusCode());


        //verify content type
        Assertions.assertEquals(response3.contentType(), "text/plain;charset=UTF-8");

        //verify we have headers named date
        //we use hasHeaderWithname method to verify header exist or not - it returns boolean
        Assertions.assertTrue(response3.headers().hasHeaderWithName("Date"));

        //how to get and header from response using header key ?
        //we use response.header(String headerName) metjod to get any header value
        System.out.println("response3.header(\"Content-Length\") = " + response3.header("Content-Length"));
        System.out.println("response3.header(\"Date\") = " + response3.header("Date"));

        //verify content length is 17
        //header method her şeyi stringe dönüştürdüğü için 17 yi de "" içinde yazdık
        Assertions.assertEquals("17",response3.header("Content-Length"));

        Assertions.assertEquals("Hello from Sparta",response3.body().asString());

    }


}
