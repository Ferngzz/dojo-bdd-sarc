package cepes.itacademy.sarc.service;

import cepes.itacademy.sarc.domain.*;
import cepes.itacademy.sarc.repository.AllocationRepository;
import java.time.LocalDate;

public class AllocationService {
    private final AllocationRepository repository;

    public AllocationService(AllocationRepository repository) {
        this.repository = repository;
    }

    public Allocation allocateResource(Collaborator collaborator, Resource resource, LocalDate date) {
        Allocation newAllocation = new Allocation(collaborator, resource, date);
        repository.saveAllocation(newAllocation);
        return newAllocation;
    }
}
