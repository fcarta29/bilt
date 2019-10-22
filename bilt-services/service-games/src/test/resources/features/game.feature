Feature: Game Stuff

    Background: Logged into application
      Given the embedded redis server is running 
      And the application data is loaded

    Scenario: Getting games and picks for week 8
        Given today is Wednesday of week 7
        And it is not gameday
        When I ask for the week 8 game picks
        Then 0 games should be returned
        And 0 game picks should be returned 

    Scenario: Getting games and picks for week 7
        Given today is Wednesday of week 7
        And it is not gameday
        When I ask for the week 7 game picks
        Then 2 games should be returned
        And 3 game picks should be returned 

    Scenario Outline: Getting games and picks as <memberName> for the week <week>
        Given my member name is <memberName>
        And today is Wednesday of week 7
        And it is not gameday
        When I ask for my week <week> game picks
        Then <games> games should be returned for <memberName>
        And <gamePicks> game picks should be returned for <memberName>
        
        Examples:
        | memberName  | week  | games | gamePicks |
        | fcarta      | 7     | 2     | 2         |
        | ctruhe      | 7     | 1     | 1         |
        | jtruhe      | 7     | 0     | 0         |
        | fcarta      | 8     | 0     | 0         |
        | ctruhe      | 8     | 0     | 0         |
        | jtruhe      | 8     | 0     | 0         |
         
    Scenario: Get games endpoint
        Given I want to use the game api
        When I send the games get request
        Then I should have a games response
        