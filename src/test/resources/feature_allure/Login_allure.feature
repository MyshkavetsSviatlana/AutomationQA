Feature: Testing login page

  Scenario: The login page title is "QA Course 01"
    Given the user is on login page
    Then the page title is "QA Course 01"

  Scenario: Email field placeholder is "Enter email"
    Given the user is on login page for email placeholder test
    Then email field placeholder should be "Enter email"

  Scenario: Empty email field causes "Required" alert
    Given the user is on login page for empty email test
    Then the email field alert should display "Required"

  Scenario: Leading spaces before email cause "Required" alert
    Given the user is on login page for email with leading spaces test
    Then the leading spaces should cause "Required" alert

  Scenario: Clicking registration link redirects to registration page
    Given the user is on login page for registration link test
    Then click on registration link redirects to registration page