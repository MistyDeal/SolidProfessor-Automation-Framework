Feature: Language Fucntionality

  Background: 
    Given User sets up environment for test automation execution  | executionSetUp
      | project_name   | sprint_number | userstory_number | testcase_type |
      | solidProfessor |            55 | US555            | UI            |

  @smoke @regression @release1.5 @sprint15
  Scenario Outline: Verify User able to add new Language
    Given User logs into OrangHRM application | loginToApp "<browser>" "<region>"
    When User clicks Admin from menu | clickElement "menu.admin"
    And User clicks qualifications dropdown | clickElement "admin.qualification"
    And User choose language from  dropdown | clickElement "qualification.languages"
    And User clicks add button | clickElement "languages.add"
    And User enters name | enterValue "languages.name" "<name>"
    And User clicks save button | clickElement "languages.save"
    Then System displays success message | messageVerify "languages.message.success" "<message>"

    Examples: 
      | region | browser | name       | message            |
      | scrum  | edge    | Soccer133  | Successfully Saved |
      | scrum  | edge    | Soccer1233 | Bad Data           |
