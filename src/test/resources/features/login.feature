Feature: Login 

  @positive @android @ios
  Scenario Outline: Successful login with valid credentials
    Given Open Nicequest login page on "<platform>"
    When I enter email "<email>"
    And I enter password "<password>"
    And I tap login button
    Then I should see "Dashboard visible"

    Examples:
      | platform | email              | password   |
      | Android  | valid.user@test.com | correct123 |
      | iOS      | valid.user@test.com | correct123 |

  @negative @android
  Scenario Outline: Unsuccessful login with invalid email
    Given Open Nicequest login page on "<platform>"
    When I enter email "<email>"
    And I enter password "<password>"
    And I tap login button
    Then I should see "Invalid email format"

    Examples:
      | platform | email         | password   |
      | Android  | bademail.com  | correct123 |
      | iOS      | bademail.com  | correct123 |

  @negative @ios
  Scenario Outline: Unsuccessful login with wrong password
    Given Open Nicequest login page on "<platform>"
    When I enter email "<email>"
    And I enter password "<password>"
    And I tap login button
    Then I should see "Incorrect password"

    Examples:
      | platform | email               | password   |
      | Android  | valid.user@test.com | wrongpass1 |
      | iOS      | valid.user@test.com | wrongpass1 |

  @negative
  Scenario Outline: Login with empty credentials
    Given Open Nicequest login page on "<platform>"
    When I enter email "<email>"
    And I enter password "<password>"
    And I tap login button
    Then I should see "<expectedMessage>"

    Examples:
      | platform | email | password | expectedMessage       |
      | Android  |       |          | Email is required     |
      | iOS      |       |          | Email is required     |
      | Android  | user@test.com |   | Password is required |
      | iOS      | user@test.com |   | Password is required |
