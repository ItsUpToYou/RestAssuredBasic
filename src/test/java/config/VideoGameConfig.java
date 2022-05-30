package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

public class VideoGameConfig {
    public static RequestSpecification vgameRequestSpec;
    public static ResponseSpecification vgameResponseSpec;

    @BeforeClass
    public static void setup() {
        vgameRequestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setBasePath("/app/")
                .setPort(8080)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        vgameResponseSpec = new ResponseSpecBuilder()
                .expectResponseTime(lessThan(3000L))
                .build();

        RestAssured.requestSpecification = vgameRequestSpec;
        RestAssured.responseSpecification = vgameResponseSpec;

    }
}
