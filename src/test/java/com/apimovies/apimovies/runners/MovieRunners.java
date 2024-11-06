package com.apimovies.apimovies.runners;


import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {
                "src/test/resources/features/get_movie.feature",
                "src/test/resources/features/post_movie.feature"
        },

        glue = {"com.apimovies.apimovies.stepdefinitions.hook",
                "com.apimovies.apimovies.stepdefinitions"
        }
)

public class MovieRunners {

}
