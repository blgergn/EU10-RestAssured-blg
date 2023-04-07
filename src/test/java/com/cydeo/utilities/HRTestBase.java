package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class HRTestBase {

    @BeforeAll
    public static void init() {
        baseURI = "http://18.234.192.234:1000/ords/hr";
    }

}
