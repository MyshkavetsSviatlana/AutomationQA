Feature: Testing login page

Scenario: The login page title is "QA Course 01"
Given The driver is set up
When the user is on login page
And the page title is "QA Course 01"
Then quit driver

Scenario: Email field placeholder is "Enter email"
Given The driver is set up for email placeholder test
When the user is on login page for email placeholder test
And email field placeholder should be "Enter email"
Then quit driver after email placeholder test

Scenario: Empty email field causes "Required" alert
Given The driver is set up for empty email test
When the user is on login page for empty email test
And the email field alert should display "Required"
Then quit driver after empty email test

Scenario: Leading spaces before email cause "Required" alert
Given The driver is set up for email with leading spaces test
When the user is on login page for email with leading spaces test
And the leading spaces should cause "Required" alert
Then quit driver after email with leading spaces test

Scenario: Clicking registration link redirects to registration page
Given the driver is set up for registration link test
When the user is on login page for registration link test
And registration link redirects to registration page
Then quit driver after registration link test