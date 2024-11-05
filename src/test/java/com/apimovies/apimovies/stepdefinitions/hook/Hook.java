package com.apimovies.apimovies.stepdefinitions.hook;

import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.actors.OnStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.util.EnvironmentVariables;
import org.junit.Before;

public class Hook {
    private EnvironmentVariables environmentVariables;

    @Before
    public void configureBaseUrl(){
        setTheStage(new OnlineCast());
        theActorCalled("Movie");
        String theRestApiBaseUrl = environmentVariables.optionalProperty("environments.qa.base.url")
                .orElse("environments.dev.base.url");

        OnStage.theActorInTheSpotlight().whoCan(CallAnApi.at(theRestApiBaseUrl));

    }
}
