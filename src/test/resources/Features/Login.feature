Feature: LoginSauceDemo
  Scenario Outline: Login success on saucedemo using scenario outline
    Given Open web url "https://www.saucedemo.com/"
    And Input username "<username>" and password "<password>"
    When Click login button
    Then Should success login and redirected to homepage
    Examples:
      |username|password|
      |standard_user|secret_sauce|
      |problem_user|secret_sauce|
      |performance_glitch_user|secret_sauce|

  Scenario Outline: Login failed wrong password on  using scenario outline
    Given Open web url "https://www.saucedemo.com/"
    And Input username "<username>" and password "<password>"
    When Click login button
    Then Failed login wrong password and showing message
    Examples:
      |username|password|
      |standard_user|qbbcjdfo|
  Scenario Outline: Login failed lockout_user  using scenario outline
    Given Open web url "https://www.saucedemo.com/"
    And Input username "<username>" and password "<password>"
    When Click login button
    Then Failed login lockout_user and showing message
    Examples:
      |username|password|
      |locked_out_user|secret_sauce|