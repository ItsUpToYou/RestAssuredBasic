import config.VideoGameConfig;
import config.VideoGamesEndPoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class ValidationExamplesTests extends VideoGameConfig {



    @Test
    public void getAllGames() {
        given().
            when()
                .get(VideoGamesEndPoints.ALL_VIDEO_GAMES).
            then()
                .log()
                .body()
                .statusCode(200);
    }

    @Test
    public void wrongEndPoint() {
        given().
            when()
                .get("http://localhost:8080/app/ZZZZ").
            then().
                    body(containsString("Not Found")).
                    statusCode(404);
    }


    @Test
    public void  checkGameIdExist(){
        given().
               pathParam("videoGameId", 5).
        when().
                get(VideoGamesEndPoints.VIDEO_GAME_ID).
        then().
                body(containsString("Adventure")).
                statusCode(200);
    }

    @Test
    public void  checkGameIdExist(){
        given().
                when().
                then().onFailMessage("fsd");


    }
}
