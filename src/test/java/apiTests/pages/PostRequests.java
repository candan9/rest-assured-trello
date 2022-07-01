package apitests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class PostRequests {
	public GetRequests getRequests = new GetRequests();
	public Authentication authentication = new Authentication();
	public Response createBoard(String boardName) {
		return RestAssured.given()
				.spec(authentication.postRequestSpecification())
				.queryParam("name", boardName)
				.when().post("/boards/");
	}
	public Response createCard(String cardName, String boardName) {
		return RestAssured.given()
				.spec(authentication.postRequestSpecification())
				.queryParam("name", cardName)
				.queryParam("idList", getRequests.getListId("To Do", boardName))
				.when().post("/cards/");
	}
	@Test
	public void createCardTest() {
		createCard("Testowa karta", "Agile Automation app");
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