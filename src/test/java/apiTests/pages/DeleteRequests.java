package apitests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class DeleteRequests {
    public GetRequests getRequests = new GetRequests();
    public Authentication authentication = new Authentication();
    public Response deleteBoard(String boardName) {
        return RestAssured.given()
                .spec(authentication.authenticationSpecification())
                .queryParam("id", getRequests.getBoardId(boardName))
                .when().delete("/boards/");
    }
    public Response deleteCard(String cardName,String boardName) {
        return RestAssured.given()
                .spec(authentication.postRequestSpecification())
                .queryParam("id", getRequests.getCardId(cardName,boardName))
                .when().delete("/cards/");
    }

    Response createList(String listName, String boardName) {
        return RestAssured.given()
                .spec(authentication.postRequestSpecification())
                .queryParam("name", listName)
                .queryParam("idBoard", getRequests.getBoardId(boardName))
                .when().post("/lists/");
    }
    @Test
    public void createListTest() {
        createList("Test list", "Agile Automation app");
    }

}