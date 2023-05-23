Feature: Get Veteran Fireman

  Scenario: Retrieve the oldest fireman who intervened on a fire in Corsica
    Given the following firemans worked on the fire in Corsica:
      | Fireman Name | Fireman Age |
      | John Doe     | 35          |
      | Jane Smith   | 42          |

    When I retrieve the oldest fireman who intervened on a fire in Corsica
    Then the veteran fireman is "Jane Smith" with age 42