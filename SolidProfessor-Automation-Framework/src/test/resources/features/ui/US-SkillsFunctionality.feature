Feature: Skills Fucntionality

  Background: 
    Given User sets up environment for test automation execution  | executionSetUp
      | project_name   | sprint_number | userstory_number | testcase_type |
      | solidProfessor |            55 | US555            | UI            |

  @smoke @regression @release1.5 @sprint15
  Scenario Outline: Verify User able to add new Skills
    Given User logs into OrangHRM application | loginToApp "<browser>" "<region>"
    When User clicks Admin from menu | clickElement "menu.admin"
    And User clicks qualifications dropdown | clickElement "admin.qualification"
    And User choose skills from  dropdown | clickElement "qualification.skills"
    And User clicks add button | clickElement "skills.add"
    And User enters name | enterValue "skills.name" "<name>"
    And User enters Description | enterValue "skills.description" "<description>"
    And User clicks save button | clickElement "skills.save"
    Then System displays success message | messageVerify "skills.message.success" "<message>"

    Examples: 
      | region | browser | name        | description | message            |
      | scrum  | edge    | Football3   | demo101     | Successfully Saved |
      | scrum  | edge    | Volleyball3 | demo101     | bad_message        |
