Feature: Login

  @android @ios
  Scenario Outline: Validate login with multiple credentials
    Given I open Nicequest login page on "<platform>"
    When I login with email "<email>" and password "<password>"
    Then I should see "<expectedResult>"

    Examples:
      | platform | email               | password   | expectedResult     |
      | android  | valid@test.com      | 123456     | Dashboard visible  |
      | android  | invalid@test.com    | 123456     | Invalid email      |
      | android  | valid@test.com      | wrongpass  | Invalid password   |
      | ios      | valid@test.com      | 123456     | Dashboard visible  |
      | ios      | invalid@test.com    | 123456     | Invalid email      |
      | ios      | valid@test.com      | wrongpass  | Invalid password   |
