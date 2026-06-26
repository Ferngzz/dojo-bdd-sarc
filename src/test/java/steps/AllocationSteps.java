package steps;

import cepes.itacademy.sarc.domain.*;
import cepes.itacademy.sarc.repository.*;
import cepes.itacademy.sarc.service.AllocationService;
import io.cucumber.java.en.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AllocationSteps {

    private CollaboratorRepository collaboratorRepo;
    private ResourceRepository resourceRepo;
    private AllocationRepository allocationRepo;

    private AllocationService allocationService;

    private Collaborator currentCollaborator;
    private Resource currentResource1;
    private Resource currentResource2;
    private LocalDate allocationDate;
    private Allocation confirmedAllocation;
    private Allocation confirmedAllocation2;
    private boolean existingAllocation;
    private Exception exceptionTriggered;

    @Given("that resources and collaborators are registered in the system")
    public void that_resources_and_collaborators_are_registered_in_the_system() {
        collaboratorRepo = new InMemoryCollaboratorRepository();
        resourceRepo = new InMemoryResourceRepository();
        allocationRepo = new InMemoryAllocationRepository();

        allocationService = new AllocationService(allocationRepo);
    }

    @Given("that the collaborator {string} wants to allocate the notebook {string}")
    public void that_the_collaborator_wants_to_allocate_the_notebook(String collaboratorId, String resourceId) {
        currentCollaborator = collaboratorRepo.findById(collaboratorId).orElseThrow();
        currentResource1 = resourceRepo.findById(resourceId).orElseThrow();
    }

    @When("they request the reservation for the date {string}")
    public void they_request_the_reservation_for_the_date(String date) {
        allocationDate = LocalDate.parse(date);

        try {
            confirmedAllocation = allocationService.allocateResource(currentCollaborator, currentResource1,
                    allocationDate);
        } catch (Exception e) {
            exceptionTriggered = e;
        }
    }

    @Then("the system should successfully confirm the allocation")
    public void the_system_should_successfully_confirm_the_allocation() {
        assertNotNull(confirmedAllocation, "A alocação deveria ter sido confirmada.");
        assertNull(exceptionTriggered, "Nenhuma exceção deveria ter sido lançada.");
    }

    @Given("the {string} is already allocated for the {string} on {string}")
    public void the_resource_is_already_allocated(String resourceId, String collaboratorId, String date) {
        currentResource1 = resourceRepo.findById(resourceId).orElseThrow();
        currentCollaborator = collaboratorRepo.findById(collaboratorId).orElseThrow();
        allocationDate = LocalDate.parse(date);

        allocationRepo.saveAllocation(new Allocation(currentCollaborator, currentResource1, allocationDate));
    }

    @When("the {string} tries to allocate the {string} for the {string}")
    public void the_collaborator_tries_to_allocate_the_resource_for_the_day(String collaboratorId, String resourceId,
            String date) {
        currentResource1 = resourceRepo.findById(resourceId).orElseThrow();
        currentCollaborator = collaboratorRepo.findById(collaboratorId).orElseThrow();
        allocationDate = LocalDate.parse(date);

        existingAllocation = allocationRepo.existsByResourceAndDate(currentResource1, allocationDate);

        if (!existingAllocation) {
            allocationRepo.saveAllocation(new Allocation(currentCollaborator, currentResource1, allocationDate));
        }
    }

    @Then("the system should inform that the allocation cannot be made")
    public void the_system_should_inform_error() {
        assertTrue(existingAllocation, "A alocação já deve existir");
        assertNull(exceptionTriggered, "Nenhuma exceção deveria ter sido lançada.");

    }

    @Given("the {string} wants to allocate {string} and {string}")
    public void the_user_wants_to_allocate_two_resources(String collaboratorId, String resourceId1,
            String resourceId2) {
        currentCollaborator = collaboratorRepo.findById(collaboratorId).orElseThrow();
        currentResource1 = resourceRepo.findById(resourceId1).orElseThrow();
        currentResource2 = resourceRepo.findById(resourceId2).orElseThrow();
    }

    @When("the reservation is requested for the {string}")
    public void the_allocation_is_requested_for_the_day(String date) {
        allocationDate = LocalDate.parse(date);
        boolean validCombination = (currentResource1 instanceof Notebook && currentResource2 instanceof Room)
                || (currentResource1 instanceof Room && currentResource2 instanceof Notebook);

        if (validCombination) {
            confirmedAllocation = allocationService.allocateResource(currentCollaborator, currentResource1,
                    allocationDate);
            confirmedAllocation2 = allocationService.allocateResource(currentCollaborator, currentResource2,
                    allocationDate);
        }

        existingAllocation = allocationRepo.existsByResourceAndDate(currentResource1, allocationDate)
                || allocationRepo.existsByResourceAndDate(currentResource2, allocationDate);

    }

    @Then("the system should inform that the allocation was successful")
    public void the_system_should_inform_that_the_allocations_was_successful() {
        assertNotNull(confirmedAllocation);
        assertNotNull(confirmedAllocation2);
    }

    @Then("the system should inform that the allocation combination cannot be made")
    public void the_system_should_inform_the_allocation_failed() {
        assertFalse(existingAllocation, "A combinação de alocações não é possível");
        assertNull(exceptionTriggered, "Nenhuma exceção deveria ter sido lançada.");
    }

}
