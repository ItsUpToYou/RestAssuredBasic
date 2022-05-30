import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

public class TestConfig {
    @BeforeClass
    public static void setup() {

        //RestAssured.proxy("localhost", 8888); checking API req and Respons with Fidler

        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/app/";
        RestAssured.port = 8080;

        RequestSpecification reqSpecif = new RequestSpecBuilder()
                                        .addHeader("Content-Type", "application/json")
                                        .addHeader("Accept", "application/json")
                                        .build();

        RestAssured.requestSpecification = reqSpecif;

        ResponseSpecification respSpecif = new ResponseSpecBuilder()
                                            .expectStatusCode(200)
                                            .build();

        RestAssured.responseSpecification = respSpecif;


    }
}
