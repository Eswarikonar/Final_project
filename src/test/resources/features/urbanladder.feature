Feature: Urban Ladder Validation

  Scenario: Verify bookshelves under price limit
    Given user launches Urban Ladder application
    When user searches for bookshelves
    Then user prints first 3 bookshelves under 15000

  Scenario: Verify sofa categories
    Given user launches Urban Ladder application
    When user captures all sofa and recliner options
    Then sofa and recliner options should be displayed

  Scenario: Verify gift card invalid email
    Given user launches Urban Ladder application
    When user opens gift card page
    Then error message should be displayed for invalid email
