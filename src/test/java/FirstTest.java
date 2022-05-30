import config.VideoGameConfig;
import config.VideoGamesEndPoints;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class FirstTest extends VideoGameConfig {
    @Test
    public void firstTest(){
        given()
                .log().all().
        when().get("videogames").
        then().
                log().all()
                .statusCode(200);
    }

    @Test
    public void testWithEndPoint() {
        get(VideoGamesEndPoints.ALL_VIDEO_GAMES)
                .then().log().all()
                .statusCode(200);
    }
}
