package cepes.itacademy.sarc.service;

import cepes.itacademy.sarc.domain.*;
import cepes.itacademy.sarc.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AllocationServiceTest {

    private CollaboratorRepository collaboratorRepo;
    private ResourceRepository resourceRepo;
    private AllocationRepository allocationRepo;
    private AllocationService allocationService;

    @BeforeEach
    void setUp() {
        collaboratorRepo = new InMemoryCollaboratorRepository();
        resourceRepo = new InMemoryResourceRepository();

        allocationRepo = new InMemoryAllocationRepository();

        allocationService = new AllocationService(allocationRepo);
    }

    @Test
    void shouldSuccessfullyAllocateAnAvailableResource() {
        Collaborator collaborator = collaboratorRepo.findById("10002020").orElseThrow();
        Resource notebook = resourceRepo.findById("CEPES1983").orElseThrow();
        LocalDate allocationDate = LocalDate.of(2026, 6, 12);

        Allocation result = allocationService.allocateResource(collaborator, notebook, allocationDate);

        assertNotNull(result, "The allocation should not be null");
        assertEquals(collaborator, result.getCollaborator(), "The collaborator should match Andre Oliveira");
        assertEquals(notebook, result.getResource(), "The resource should match Notebook CEPES1983");
        assertEquals(allocationDate, result.getDate(), "The date should match 2026-06-12");

        assertTrue(allocationRepo.existsByResourceAndDate(notebook, allocationDate),
                "The allocation should be persisted in the repository");
    }

    @Test
    void shouldSucessfullyAllocateANotebookAndARoom() {
        Collaborator collaborator = collaboratorRepo.findById("20002026").orElseThrow();
        Resource notebook = resourceRepo.findById("CEPES1995").orElseThrow();
        Resource room = resourceRepo.findById("512").orElseThrow();
        LocalDate date = LocalDate.parse("2026-06-12");

        boolean validCombination = (notebook instanceof Notebook && room instanceof Room)
                || (room instanceof Room && notebook instanceof Notebook);

        if (validCombination) {
            allocationService.allocateResource(collaborator, room, date);
            allocationService.allocateResource(collaborator, notebook, date);
        }

        List<Allocation> allocations = allocationRepo.findByCollaboratorIdAndDate(collaborator, date);

        assertTrue(validCombination);
        assertTrue(allocations.getFirst().getResource() instanceof Room);
        assertTrue(allocations.getLast().getResource() instanceof Notebook);
    }

    @Test
    void shouldNotCreateAllocationIfTheResourceIsAllocatedInTheDay() {
        Collaborator collaborator = collaboratorRepo.findById("20002026").orElseThrow();
        Resource notebook = resourceRepo.findById("CEPES1995").orElseThrow();
        LocalDate date = LocalDate.parse("2026-06-12");

        allocationService.allocateResource(collaborator, notebook, date);

        var existingAllocation = allocationRepo.existsByResourceAndDate(notebook, date);

        if (!existingAllocation) {
            allocationService.allocateResource(collaborator, notebook, date);
        }

        assertTrue(existingAllocation);
    }

    @Test
    void ShouldNotAllocateNotebookAndLabCombination(){
        Collaborator collaborator = collaboratorRepo.findById("20002026").orElseThrow();
        Resource notebook = resourceRepo.findById("CEPES1995").orElseThrow();
        Resource lab = resourceRepo.findById("312").orElseThrow();
        LocalDate date = LocalDate.parse("2026-06-12");

        Allocation notebookAllocation = allocationService.allocateResource(collaborator, notebook, date);
        Allocation labAllocation = null;

        boolean exists = allocationRepo.existsByResourceAndDate(notebook, date);

        if (!exists){
            allocationService.allocateResource(collaborator, lab, date);
        }

        assertNotNull(notebookAllocation);
        assertNull(labAllocation);
        
    }
}