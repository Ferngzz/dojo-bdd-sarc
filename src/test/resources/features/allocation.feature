# language: en
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