package apitests;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class Authentication {
	String url = "https://api.trello.com/1/";
	String apiKey = "79d5f3d261786c920af61fc22d4d6841";
	String token = "56d4cc6ca42db4e927e2d2e76da062130604ef79925ca1104ef5556a2d2666e9";

	public RequestSpecification authenticationSpecification(){
		return RestAssured.given().baseUri(url)
				.param( "key", apiKey)
				.param("token", token);
	}
	public RequestSpecification postRequestSpecification() {
		return RestAssured.given().baseUri(url)
				.contentType("application/json")
				.queryParam( "key", apiKey)
				.queryParam("token", token);
	}
}
