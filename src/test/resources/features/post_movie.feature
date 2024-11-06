#language: en
# Authors: jonathan.granda@udea.edu.co, cristian.munoz3@udea.edu.co

Feature: As a user of the movie application, I want to create a new movie

  Background:
    Given I am connected to the movie application

  Scenario: Create a new movie successfully
    When I send a POST request with valid movie data
    Then the movie should be created successfully
    And the response should contain the movie details

  Scenario: Create a movie with invalid data
    When I send a POST request with invalid movie data
    Then I should receive a bad request error