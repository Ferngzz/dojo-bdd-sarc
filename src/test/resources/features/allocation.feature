Feature: Resource Allocation
  As an IT Academy collaborator
  I want to be able to reserve notebooks, rooms, and labs
  So that students can learn

  Background:
    Given that resources and collaborators are registered in the system

  Scenario: Successful allocation of an available resource
    Given that the collaborator "10002020" wants to allocate the notebook "CEPES1983"
    When they request the reservation for the date "2026-06-12"
    Then the system should successfully confirm the allocation

  Scenario: Resource unavailable for the rest of the day
    Given the "<Recurso>" is already allocated for the "<Dia>"
    When the "<Usuario>" tries to allocate the "<Recurso>" for the "<Dia>"
    Then the system should inform that the allocation cannot be made
  
  # Only one user can allocate a specific resource
  # for a given day.
  Scenario: Resource is allocated to a single user
    When the resource is allocated by a "<Usuario>"
    Then the resource will be allocated to the "<Usuario>"

  # A user can reserve a notebook and a room for the same day,
  # but cannot reserve any other combinations of resources
  # (such as a lab along with another resource).
  Scenario: Notebook and Room successfully allocated
    Given the "<Usuario>" wants to allocate "<Recurso1>" and "<Recurso2>"
    When the reservation is requested for the "<Dia>"
    Then the system should inform that the allocation was successful
      | Usuario | Recurso1 | Recurso2 |
      | Bob     | Notebook | Sala     |

  Scenario: Notebook and Lab not allocated
    Given the "<Usuario>" wants to allocate "<Recurso1>" and "<Recurso2>"
    When the reservation is requested for the "<Dia>"
    Then the system should inform that the allocation cannot be made
      | Usuario | Recurso1 | Recurso2    |
      | Jorge   | Notebook | Laboratorio |
      | Galo    | Sala     | Laboratorio |
