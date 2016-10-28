Feature: Search Leave
  	
  Background:
  Then I should see "Account LogIn"
  Then I enter text "louie.d.p.mandigal" into field with id "loginEmail"
  Then I enter text "Qwerty3!" into field with id "loginPassword"
  Then I press view with id "loginButton"
  Then I wait for the "SEARCH BY EID" button to appear
  
    	
  Scenario: Search Leave    
  	Given I press the "Search by EID" button
  	Then I enter text "louie.d.p.mandigal" into field with id "searchName"
  	Then I hide keyboard
  	Then I press "SEARCH"
  	
  Scenario: User Cancels	
  	Given I press the "Search by EID" button  	
  	Then I press view with id "searchCancel"
  	Then I should see "Search by EID"
  	
  	
   	