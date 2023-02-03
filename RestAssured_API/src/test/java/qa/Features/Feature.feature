Feature: Api Automation Tests

  @createUser
  Scenario Outline: _POST_Validate that a user is created succesfully
    Given Setup "POST" payload with "<id>" "<Fname>" "<Lname>" "<password>" details
    When New user Creation Request is made
    Then Validate that the status code is "200"
    And Validate that Response is not null
    And Validate that the response time is optimum

    Examples: 
      | id  | Fname   | Lname   | password  |
      | 101 | Ted     | Mosby   | Robin123  |
      | 102 | Barney  | Stinson | Legendary |
      | 103 | Marshal | Ericson | Lily123   |

  @EditUser
  Scenario: _PUT_Validate that User details can be edited succesfully
    Given Setup "PUT" payload with "101" "Ted" "Mosby" "Robin123" details
    When Update user request is made for "TedMosby"
    Then Validate that the status code is "200"
    And Validate that the response time is optimum
    And Validate that the details are updated for "TedMosby"

  @DeleteUser
  Scenario: _DELETE_Validate that a user can be deleted succesfully
    When Delete user request is made for "TedMosby"
    Then Validate that the status code is "200"
    And Validate that the response has the user name "TedMosby"
    And Validate that the response time is optimum
