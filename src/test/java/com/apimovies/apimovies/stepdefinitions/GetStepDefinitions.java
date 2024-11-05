package com.apimovies.apimovies.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

public class GetStepDefinitions {

    private RequestSpecification request;
    private Response response;
    private int movieId;


    @Given("I am connected to the movie application")
    public void iAmConnectedToTheMovieApplication() {
        request = given()
                .baseUri("http://localhost:8080")
                .contentType("application/json");

    }

    @When("I send a specific movie's id to the movie API")
    public void iSendASpecificMovieIdToTheMovieAPI() {
        movieId = 1;
        response = request
                .when()
                .get("/movies/" + movieId);
    }

    @Then("I get all the information about that movie in the correct format")
    public void iGetAllTheInformationAboutThatMovie() {
        response.then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("title", notNullValue())
                .body("description", notNullValue())
                .body("releaseDate", matchesPattern("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}"))
                .body("availableOnDvd", isA(Boolean.class));

        response.then().assertThat()
                .body("$", hasKey("id"))
                .body("$", hasKey("title"))
                .body("$", hasKey("description"))
                .body("$", hasKey("releaseDate"))
                .body("$", hasKey("availableOnDvd"));

    }

    @When("I request all movies from the API")
    public void iRequestAllMoviesFromTheAPI() {
        response = request
                .when()
                .get("/movies");
    }
    @Then("I get a list of movies in the correct format")
    public void iGetAListOfMoviesInTheCorrectFormat() {
        response.then()
                .statusCode(200)
                .body("", isA(java.util.List.class))
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("title"))
                .body("[0]", hasKey("description"))
                .body("[0]", hasKey("releaseDate"))
                .body("[0]", hasKey("availableOnDvd"));

        response.then().assertThat()
                .body("every { it.id != null }", is(true))
                .body("every { it.title != null }", is(true))
                .body("every { it.description != null }", is(true))
                .body("every { it.releaseDate =~ /\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}/ }", is(true))
                .body("every { it.availableOnDvd in [true, false] }", is(true));
    }

}
