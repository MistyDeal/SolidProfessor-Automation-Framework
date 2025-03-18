Feature: JobTitles Fucntionality

  Background: 
    Given User sets up environment for test automation execution  | executionSetUp
      | project_name   | sprint_number | userstory_number | testcase_type |
      | solidProfessor |            55 | US555            | UI            |

  @regression @release1.5 @sprint15 @smoke
  Scenario Outline: Verify User able to add new Job Title
    Given User logs into OrangHRM application | loginToApp "<browser>" "<region>"
    When User clicks Admin from menu | clickElement "menu.admin"
    And User clicks job dropdown | clickElement "admin.job"
    And User choose job titles from  dropdown | clickElement "job.jobTitles"
    And User clicks add button | clickElement "jobTitles.add"
    And User enters Job Title | enterValue "jobTitles.title" "<titleValue>"
    And User enters Job Description | enterValue "jobTitles.description" "<descriptionValue>"
    And User enters Job Notes | enterValue "jobTitles.note" "<noteValue>"
    And User clicks save button | clickElement "jobTitles.save"
    Then System displays success message | messageVerify "jobTitles.message.success" "<message>"

    Examples: 
      | region | browser | titleValue | descriptionValue | noteValue    | message            |
      | scrum  | chrome  | Testabc333 | demo101          | exampleTest1 | Successfully Saved |
