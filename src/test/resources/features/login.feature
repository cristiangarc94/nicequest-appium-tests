Feature: Nicequest Login

  Scenario: Successful login
    Given Open Nicequest login page
    When I enter email "garc6904@gmail.com"
    And I enter password "Prueba1234"
    And I tap login button
    Then I should see the dashboard


# Feature: Nicequest Login - Negative cases

# Scenario: Login fails with empty fields
#   Given Open Nicequest login page
#   When I tap login button
#   Then I should see an error message "Email and password are required"

# Scenario: Login fails with invalid email format
#   Given Open Nicequest login page
#   When I enter email "garc6904"
#   And I enter password "Prueba1234"
#   And I tap login button
#   Then I should see an error message "Invalid email format"

# Scenario: Login fails with invalid password
#   Given Open Nicequest login page
#   When I enter email "garc6904@gmail.com"
#   And I enter password "wrongpass"
#   And I tap login button
#   Then I should see an error message "Invalid password"

# Scenario: Login fails with wrong credentials
#   Given Open Nicequest login page
#   When I enter email "wrong@domain.com"
#   And I enter password "wrongpass"
#   And I tap login button
#   Then I should see an error message "Invalid username or password"
