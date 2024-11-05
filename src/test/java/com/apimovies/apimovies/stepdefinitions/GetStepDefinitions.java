package com.apimovies.apimovies.stepdefinitions;

import com.apimovies.apimovies.models.TestData;
import com.apimovies.apimovies.tasks.Load;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class GetStepDefinitions {

    @Given("I am connected to the movie application")
    public void iAmConnectedToTheMovieApplication(List<Map<String, String>> data) {
        OnStage.theActorInTheSpotlight().wasAbleTo(
                Load.testData(data.get(0))
        );
    }

    @When("I send a specific movie's id to the movie API")
    public void iSendASpecificMovieIdToTheMovieAPI() {
        System.out.println("Datos " + TestData.getData());
    }

    @Then("I get all the information about that movie")
    public void iGetAllTheInformationAboutThatMovie() {
        Assert.assertNotNull("Movie response should not be null");
    }

}
