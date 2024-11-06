package com.apimovies.apimovies.runners;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {
                "src/test/resources/features/put_movie.feature"
        },
        glue = {
                "com.apimovies.apimovies.stepdefinitions.hook",
                "com.apimovies.apimovies.stepdefinitions"
        }
)

public class PutMovieRunners {
}
