Feature: Logout Functionality

  Background: 
    Given User sets up environment for test automation execution  | executionSetUp
      | project_name   | sprint_number | userstory_number | testcase_type |
      | solidProfessor |            55 | US555            | UI            |

  @regression @release1.5 @sprint15 @smoke
  Scenario Outline: Verify the logout option is displayed and user able to logout
    Given User logs into OrangHRM application | loginToApp "<browser>" "<region>"
    When User clicks username dropdown | clickElement "menu.usernamedrp"
    Then User verifies logout option is displayed | elementDisplayed "menu.usernamedrp.logout"
    When User clicks logout option from dropdown | clickElement "menu.usernamedrp.logout"
    Then User is redirected to login page | verifyPageTitle "<pageTitle>"

    @scrum
    Examples: 
      | region | browser | pageTitle |
      | scrum  | chrome  | OrangeHRM |
