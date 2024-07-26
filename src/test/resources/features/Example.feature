Feature: Login in underc0de forum

  @ExampleTag
  Scenario Outline: User login in the Underc0de forum
    Given the user is on the home screen of Underc0de.org
    When the user click the "FORO" button
    And the user click the "INGRESAR" button of the forum
    And the user complete te user information. Usuario: "<user>" Contraseña: "<pass>"
    And the user click the "INGRESAR" button of the forum modal
    Then the user verifies that they are logged in.
    Examples:
      | user         | pass          |
      | autoc0de     | Underc0de     |
      | autoc0deFail | undercodefail |