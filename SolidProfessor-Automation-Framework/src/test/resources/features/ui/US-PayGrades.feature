Feature: PayGrade Fucntionality

  Background: 
    Given User sets up environment for test automation execution  | executionSetUp
      | project_name   | sprint_number | userstory_number | testcase_type |
      | solidProfessor |            55 | US555            | UI            |

  @regression @release1.5 @sprint15 @smoke
  Scenario Outline: Verify User able to add new Job Title
    Given User logs into OrangHRM application | loginToApp "<browser>" "<region>"
    When User clicks Admin from menu | clickElement "menu.admin"
    And User clicks job dropdown | clickElement "admin.job"
    And User choose Pay Grades from  dropdown | clickElement "job.payGrades"
    And User clicks add button | clickElement "payGrades.add"
    And User enters Pay Grade Name | enterValue "payGrades.name" "<payGradeName>"
    And User clicks save button | clickElement "payGrades.save"

    Examples: 
      | region | browser | payGradeName    |
      | scrum  | chrome  | pay-US-Dollar13 |
      | scrum  | chrome  | pay-US-Dollar23 |
