#language: en
# Authors: jonathan.granda@udea.edu.co, cristian.munoz3@udea.edu.co

Feature: As an user of the movie application, I want to get all the information about a specific movie


  Scenario: Get information about one specific movie sending its id
    Given I am connected to the movie application
      | id |
      | 1  |
    When I send a specific movie's id to the movie API
    Then I get all the information about that movie
