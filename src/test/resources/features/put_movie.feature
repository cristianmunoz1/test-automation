#language: en
# Authors: jonathan.granda@udea.edu.co, cristian.munoz3@udea.edu.co

Feature: As a user of the movie application, I want to update an existing movie

  Background:
    Given I am connected to the movie application

  Scenario: Update an existing movie successfully
    When I send a PUT request with valid movie data to update movie with id "3"
    Then the movie should be updated successfully
    And the response should contain the updated movie details

  Scenario: Update a movie with invalid data
    When I send a PUT request with invalid movie data to update movie with id "3"
    Then I should receive a bad request error for update

  Scenario: Update a non-existent movie
    When I send a PUT request to update movie with non-existent id "999"
    Then I should receive a not found error