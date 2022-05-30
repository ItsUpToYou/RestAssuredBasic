import config.FootballApiConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;

public class GroovyPathJSONTests extends FootballApiConfig {
    @Test
    public void extractMapOfElemetsWithFind(){
        Response response = get("competitions/2021/teams");
        Map<String, ?> allTeamDataForSingleTeam = response.path("teams.find {it.area.id == 2072}");
        System.out.println("Map of Team Data = " + allTeamDataForSingleTeam);
    }

    @Test
    public void extractSingleValueWithFind(){
        Response response = get("teams/");
        String player = response.path("squad.find {it.shirtNumber == 23}.name");
        System.out.println("Name of the player = " + player);
    }

    @Test
    public void extractAllValuesWithFindAll(){
        Response response = get("teams/60");
        List<String> allPlayers = response.path("squad.findAll {it.shirtNumber >= 23}.name");
        System.out.println("Name of the player = " + allPlayers);
    }


    @Test
    public void extractSingleValueWithHighestNumber(){
        Response response = get("teams/60");
        String playerName = response.path("squad.max {it.shirtNumber}.name");
        System.out.println("Player with highest shirt number = " + playerName);
    }

    @Test
    public void extractMultipleValuesAndSumThem(){
        Response response = get("teams/5");
        int sumId = response.path("squad.collect {it.id}.sum()");
        System.out.println("Sum of all Id`s = " + sumId);
    }

    @Test
    public void extractMapOfObjectWithFindAndFindAll(){
        Response response = get("teams/62");
        Map<String, ?> datailsOfCertainPositions = response.path("squad.findAll {it.position == 'Defence'}.find {it.nationality == 'Germany'}");
        System.out.println("Details of player:  " + datailsOfCertainPositions);
    }

    @Test
    public void extractMultipleFindAll() {
        Response response = get("teams/57");
        String position = "Offence";
        String nationality = "England";
        ArrayList<Map<String, ?>> allPlaersCertainNational = response.path("squad.findAll { it.position == '%s' }.findAll { it.nationality == '%s' }",
                position, nationality);
        System.out.println(allPlaersCertainNational);
    }
}
