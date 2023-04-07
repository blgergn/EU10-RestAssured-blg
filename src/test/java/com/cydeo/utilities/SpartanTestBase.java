package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init() {
        baseURI = "http://18.234.192.234:8000";
    }

    //get ip address from configurations

}
