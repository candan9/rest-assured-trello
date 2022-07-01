package apitests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class GetRequests {
	Authentication authentication = new Authentication();

	public Response displayBoards() {
		Response response = RestAssured.given()
				.spec(authentication.authenticationSpecification())
				.when().get("/members/me/boards");
		return response;
	}
	@Test
	public void jsonTest() {
		Response response = displayBoards();
		List<Map> boards = response.jsonPath().get();
		System.out.println(boards.stream().filter(board ->
				board.get("name").equals("Agile Automation app")).findFirst().get().get("id"));
	}
	@Test
	public void displayBoardDetailsTest() {
		displayBoards().prettyPrint();
	}
	@Test
	public void displayPermissionLevelTest() {
		System.out.println(displayBoards().jsonPath().get("prefs.permissionLevel[0]").toString());
	}
	@Test
	public void displayBoardIdTest() {
		System.out.println(getBoardId("Agile Automation app"));
	}
	public String getBoardId(String boardName) {
		Response response = displayBoards();
//Simple resolution
		int i = 0;
		while (!response.jsonPath().getString("name[" + String.valueOf(i) +
				"]").equals(boardName)) {
			i++;
		}
		return response.jsonPath().getString("id[" + String.valueOf(i) + "]");
	}
	public Response getCards(String boardName) {
		return RestAssured.given()
				.spec(authentication.authenticationSpecification())
				.when().get("/boards/"+getBoardId(boardName)+"/cards");
	}
	public String getCardId(String cardName, String boardName) {
		Response response = getCards(boardName);
		int i = 0;
		while (!response.jsonPath().getString("name[" + String.valueOf(i) +
				"]").equals(cardName)) {
			i++;
		}
		return response.jsonPath().getString("id[" + String.valueOf(i) + "]");
	}
	@Test
	public void displayCards() {
		getCards("Agile Automation app").prettyPrint();
	}
	@Test
	public void displayCardId() {
		System.out.println(getCardId("Cookies", "Agile Automation app"));
	}

	public Response getLists(String boardName) {
		return RestAssured.given()
				.spec(authentication.authenticationSpecification())
				.when().get("/boards/"+getBoardId(boardName)+"/lists");
	} //"Agile Automation app"
	public String getListId(String listName, String boardName) {
		Response response = getLists(boardName);
		int i = 0;
		while (!response.jsonPath().getString("name[" + String.valueOf(i) +
				"]").equals(listName)) {
			i++;
		}
		return response.jsonPath().getString("id[" + String.valueOf(i) + "]");
	}
	@Test
	public void displayLists() {
		getLists("Agile Automation app").prettyPrint();
	}
	@Test
	public void displayListsId() {
		System.out.println(getListId("To Do", "Agile Automation app"));
	}
}