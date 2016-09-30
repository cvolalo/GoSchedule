Feature: View Leave
  	
  Background:
  Then I should see "Account LogIn"
  Then I enter text "louie.d.p.mandigal" into field with id "loginEmail"
  Then I enter text "Qwerty3!" into field with id "loginPassword"
  Then I press view with id "loginButton"
  Then I wait for the "File Leave" button to appear
  
  Scenario: View Leave      	
  	Then I press view with id "date_icon"
  	Then I should see "Leaves"
   	