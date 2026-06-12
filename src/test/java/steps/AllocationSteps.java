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
    private Resource currentResource;
    private LocalDate allocationDate;
    private Allocation confirmedAllocation;
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
        currentResource = resourceRepo.findById(resourceId).orElseThrow();
    }

    @When("they request the reservation for the date {string}")
    public void they_request_the_reservation_for_the_date(String date) {
        allocationDate = LocalDate.parse(date);

        try {
            confirmedAllocation = allocationService.allocateResource(currentCollaborator, currentResource, allocationDate);
        } catch (Exception e) {
            exceptionTriggered = e;
        }
    }

    @Then("the system should successfully confirm the allocation")
    public void the_system_should_successfully_confirm_the_allocation() {
        assertNotNull(confirmedAllocation, "A alocação deveria ter sido confirmada.");
        assertNull(exceptionTriggered, "Nenhuma exceção deveria ter sido lançada.");
    }
}
