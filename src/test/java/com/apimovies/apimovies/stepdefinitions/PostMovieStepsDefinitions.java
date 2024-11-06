
package com.apimovies.apimovies.stepdefinitions;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostMovieStepsDefinitions {

    private Response response;
    private JSONObject movieRequest;

    @When("I send a POST request with valid movie data")
    public void iSendAPOSTRequestWithValidMovieData() throws JSONException {
        movieRequest = new JSONObject();
        movieRequest.put("title", "Movie 3");
        movieRequest.put("description", "Movie 3 Description");
        movieRequest.put("releaseDate", "2023-01-01T01:12:12");
        movieRequest.put("availableOnDvd", true);

        response = given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(movieRequest.toString())
                .when()
                .post("/movies");
    }

    @Then("the movie should be created successfully")
    public void theMovieShouldBeCreatedSuccessfully() {
        response.then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Then("the response should contain the movie details")
    public void theResponseShouldContainTheMovieDetails() throws JSONException {
        response.then()
                .body("title", equalTo(movieRequest.getString("title")))
                .body("description", equalTo(movieRequest.getString("description")))
                .body("releaseDate", equalTo(movieRequest.getString("releaseDate")))
                .body("availableOnDvd", equalTo(movieRequest.getBoolean("availableOnDvd")));
    }

    @When("I send a POST request with invalid movie data")
    public void iSendAPOSTRequestWithInvalidMovieData() throws JSONException {
        JSONObject invalidMovieRequest = new JSONObject();
        invalidMovieRequest.put("title", ""); // título vacío
        invalidMovieRequest.put("description", "Movie Description");
        invalidMovieRequest.put("releaseDate", "invalid-date"); // fecha inválida
        invalidMovieRequest.put("availableOnDvd", true);

        response = given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(invalidMovieRequest.toString())
                .when()
                .post("/movies");
    }

    @Then("I should receive a bad request error")
    public void iShouldReceiveABadRequestError() {
        response.then()
                .statusCode(400);
    }
}