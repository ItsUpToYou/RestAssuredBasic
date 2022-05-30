package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

public class FootballApiConfig {

    public static RequestSpecification fbReqSpec;
    public static ResponseSpecification fbRespSpec;

    @BeforeClass
    public static void setup(){
        fbReqSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.football-data.org/")
                .setBasePath("/v2/")
                .addHeader("X-Auth-Token", "244f689001e64e93a0d1a84f34b50fb6")
                .addHeader("X-Response-Control", "minified")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        fbRespSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
        RestAssured.responseSpecification = fbRespSpec;
        RestAssured.requestSpecification = fbReqSpec;
    }
}
