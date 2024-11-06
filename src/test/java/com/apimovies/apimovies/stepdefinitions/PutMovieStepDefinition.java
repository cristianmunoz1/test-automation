package com.apimovies.apimovies.stepdefinitions;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PutMovieStepDefinition {
    private Response response;
    private JSONObject movieRequest;
    @When("I send a PUT request to update movie with id {long}")
    public void iSendAPUTRequestToUpdateMovie(Long movieId) throws JSONException {
        movieRequest = new JSONObject();
        movieRequest.put("title", "Movie 3 - Update");
        movieRequest.put("description", "Movie 3 Description - Update");
        movieRequest.put("releaseDate", "2023-01-01T01:12:12");
        movieRequest.put("availableOnDvd", true);

        response = given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(movieRequest.toString())
                .when()
                .put("/movies/" + movieId);
    }

    @Then("the movie should be updated successfully")
    public void theMovieShouldBeUpdatedSuccessfully() {
        response.then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @When("I send a PUT request with invalid data to movie with id {long}")
    public void iSendAPUTRequestWithInvalidData(Long movieId) throws JSONException {
        JSONObject invalidMovieRequest = new JSONObject();
        invalidMovieRequest.put("title", "");
        invalidMovieRequest.put("description", "");
        invalidMovieRequest.put("releaseDate", "invalid-date");
        invalidMovieRequest.put("availableOnDvd", true);

        response = given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(invalidMovieRequest.toString())
                .when()
                .put("/movies/" + movieId);
    }
}
