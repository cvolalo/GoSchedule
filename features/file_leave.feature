Feature: Login feature

  Background:
  Then I should see "Account LogIn"
  Then I enter text "louie.d.p.mandigal" into field with id "loginEmail"
  Then I hide keyboard
  Then I enter text "Qwerty3!" into field with id "loginPassword"
  Then I hide keyboard
  Then I press view with id "loginButton"
  Then I wait for the "File Leave" button to appear
  	
  Scenario: Premature Submit  	
  	Given I press the "File Leave" button
  	Then I should see "File Leave"
  	Then I press view with id "leaveSubmit"
  	Then I should see "File Leave"

  Scenario: Successful Filing
    Given I press the "File Leave" button
    Then I should see "File Leave"
	Then I enter text "louie.d.p.mandigal" into field with id "leaveName"	
	Then I hide keyboard
	Then I press view with id "leaveDate"
	Then I press view with id "leaveDate"
	Given I press the "OK" button	
	Then I select "Sick Leave" from "leaveType"
	Then I press view with id "radio0"
	Then I press "Submit"	
	Then I should see "File Leave"

  Scenario: User Cancels	
  	Given I press the "File Leave" button
  	Then I should see "File Leave"
  	Then I press view with id "leaveCancel"
  	Then I should see "File Leave"