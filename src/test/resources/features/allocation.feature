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
    Given the "<Recurso>" is already allocated for the "<Usuario>" on "<Dia>"
    When the "<Usuario>" tries to allocate the "<Recurso>" for the "<Dia>"
    Then the system should inform that the allocation cannot be made

    Examples:
      | Usuario  | Recurso   | Dia        |
      | 10002020 | CEPES1983 | 2026-06-12 |
  # Only one user can allocate a specific resource
  # for a given day.
  # Scenario: Resource is allocated to a single user
  #   When the resource is allocated by a "<Usuario>"
  #   Then the resource will be allocated to the "<Usuario>"
  # A user can reserve a notebook and a room for the same day,
  # but cannot reserve any other combinations of resources
  # (such as a lab along with another resource).

  Scenario: Notebook and Room successfully allocated
    Given the "<Usuario>" wants to allocate "<Recurso1>" and "<Recurso2>"
    When the reservation is requested for the "<Dia>"
    Then the system should inform that the allocation was successful

    Examples:
      | Usuario  | Recurso1  | Recurso2 | Dia        |
      | 20002026 | CEPES1995 |      512 | 2026-06-12 |

  Scenario: Invalid allocation combination
    Given the "<Usuario>" wants to allocate "<Recurso1>" and "<Recurso2>"
    When the reservation is requested for the "<Dia>"
    Then the system should inform that the allocation combination cannot be made

    Examples:
      | Usuario  | Recurso1  | Recurso2 | Dia        |
      | 10002020 |       512 |      411 | 2026-06-12 |
      | 20002026 |       511 |      312 | 2026-06-12 |
      | 20002026 | CEPES1995 |      312 | 2026-06-12 |
# resources.add(new Notebook("CEPES1983", LocalDate.of(2023, 6, 1), "Dell Latitude"));
#     resources.add(new Notebook("CEPES1995", LocalDate.of(2023, 6, 1), "Dell Inspiron"));
#     resources.add(new Notebook("CEPES2017", LocalDate.of(2024, 2, 15), "Dell Alienware"));
#     resources.add(new Room("512", 30, false));
#     resources.add(new Room("511", 35, true));
#     resources.add(new Room("401", 40, true));
#     resources.add(new Lab("411", 30, "O que o BlueJ ja viu nesse lab o copilot nao tem armazenado"));
#     resources.add(new Lab("312", 30, "Maquinas formatadas em janeiro de 2026, dual boot"));
#     resources.add(new Lab("LAPRO", 60, "Duvidas peguntar para Giba"));
# collaborators.add(new Collaborator("10002020", "Andre Oliveira", "Analista Administrativo", LocalDate.of(2020, 1, 1)));
# collaborators.add(new Collaborator("20002026", "Gabriel Velloso", "Aluno de Mestrado", LocalDate.of(2025, 6, 12)));
# collaborators.add(new Collaborator("20002024", "Natalya Goelzer", "Aluna de Doutorado", LocalDate.of(2024, 3, 1)));
