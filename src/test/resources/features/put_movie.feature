#language: en
# Authors: jonathan.granda@udea.edu.co, cristian.munoz3@udea.edu.co
  Feature: As a user of the movie application, I want to edit a existing movie

    Background:
      Given I am connected to the movie application

    Scenario: Update a movie successfully
      When I send a PUT request to update movie with id 3
      Then the movie should be updated successfully
      And the response should contain the movie details

    Scenario: Update a movie with invalid data
      When I send a PUT request with invalid data to movie with id 3
      Then I should receive a bad request error