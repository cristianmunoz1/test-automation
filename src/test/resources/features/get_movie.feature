#language: en
# Authors: jonathan.granda@udea.edu.co, cristian.munoz3@udea.edu.co

Feature: As an user of the movie application, I want to get all the information about a specific movie

Background:
  Given I am connected to the movie application

  Scenario: Get a specific movie
    When I send a specific movie's id to the movie API
    Then I get all the information about that movie in the correct format

  Scenario: Get all movies
    When I request all movies from the API
    Then I get a list of movies in the correct format