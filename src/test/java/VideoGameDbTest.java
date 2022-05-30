import config.VideoGameConfig;
import config.VideoGamesEndPoints;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.*;

public class VideoGameDbTest extends VideoGameConfig {
    @Test
    public void getAllGames() {
        given().
        when()
            .get(VideoGamesEndPoints.ALL_VIDEO_GAMES).
        then()
            .statusCode(200);
    }



    @Test
    public void createNewGameByJson() {
        String gameBodyJson = "{\n" +
                "  \"id\": 13,\n" +
                "  \"name\": \"Estafet\",\n" +
                "  \"releaseDate\": \"2022-05-20T06:10:42.994Z\",\n" +
                "  \"reviewScore\": 0,\n" +
                "  \"category\": \"Test\",\n" +
                "  \"rating\": \"Stars\"\n" +
                "}";

        given()
                .body(gameBodyJson).
        when().
                post(VideoGamesEndPoints.ALL_VIDEO_GAMES).
        then();

    }

    @Test
    public void createNewGameByXML() {
        String gameBodyXml =  "<videoGame category=\"Shooter\" rating=\"Universal\">\n" +
                "    <id>15</id>\n" +
                "    <name>Resident Evil 7</name>\n" +
                "    <releaseDate>2005-10-01T00:00:00+03:00</releaseDate>\n" +
                "    <reviewScore>18</reviewScore>\n" +
                "  </videoGame>";

        given()
                .body(gameBodyXml)
                .header("Accept", "application/xml")
                .header("Content-Type", "application/xml").
        when()
                .post(VideoGamesEndPoints.ALL_VIDEO_GAMES).
        then();
    }

    @Test
    public void updateGame(){
        String updateBodyJson = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"LAST ChangedGame\",\n" +
                "  \"releaseDate\": \"2022-05-20T10:07:07.132Z\",\n" +
                "  \"reviewScore\": 6,\n" +
                "  \"category\": \"ChangedCategory\",\n" +
                "  \"rating\": \"17\"\n" +
                "}";

        given().
                body(updateBodyJson).
        when().
                put("videogames/1").
        then().
                statusCode(200);
    }

    @Test
    public void deleteGame(){
        given().
        when()
                .delete("videogames/1").
        then()
                .statusCode(200);;
    }

    @Test
    public void getGameId(){
        given()
                .pathParam("videoGameId", 5).
        when()
                .get(VideoGamesEndPoints.VIDEO_GAME_ID).
        then()
                .statusCode(200);
    }

    @Test
    public void testVideoGameSerializationByJson(){
        VideoGame videoGame = new VideoGame("30", "2021-07-21", "StarCraft", "Mature", "22", "Shooter");
        given().
                body(videoGame).
        when().
                post(VideoGamesEndPoints.ALL_VIDEO_GAMES).
        then()
                .statusCode(200);
    }

    @Test
    public void testVideoGameSchemaXML(){
        given().
                pathParam("videoGameId", 7).
                header("Content-Type", "application/xml").
                header("Accept", "application/xml").
        when().
                get(VideoGamesEndPoints.VIDEO_GAME_ID).
        then().
                body(matchesXsdInClasspath("VideoGameXSD.xsd"))
                .statusCode(200);

    }

    @Test
    public void testVideoGameSchemaJSON(){
        given().
                pathParam("videoGameId", 7).
        when().
                get(VideoGamesEndPoints.VIDEO_GAME_ID).
        then().
                body(matchesJsonSchemaInClasspath("jsonSchema.json"))
                .statusCode(200);

    }

    @Test
    public void ConvertJSONToPojo(){
        Response response =
                given()
                        .pathParam("videoGameId", 5).
                when()
                        .get(VideoGamesEndPoints.VIDEO_GAME_ID);

        VideoGame videoGame = response.getBody().as(VideoGame.class);
        System.out.println(videoGame);
    }

    @Test
    public void captureResponseTime(){
        long responseTime = get(VideoGamesEndPoints.ALL_VIDEO_GAMES).time();
        System.out.println("response time in MS: " + responseTime);
    }

    @Test
    public void assertOnResponseTime(){
        when().
                get(VideoGamesEndPoints.ALL_VIDEO_GAMES).
                then().
                time(lessThan(1000L));
    }
}
