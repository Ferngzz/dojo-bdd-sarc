package cepes.itacademy.sarc.repository;

import cepes.itacademy.sarc.domain.Allocation;
import cepes.itacademy.sarc.domain.Collaborator;
import cepes.itacademy.sarc.domain.Resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryAllocationRepository implements AllocationRepository {
    private final List<Allocation> allocations = new ArrayList<>();

    @Override
    public void saveAllocation(Allocation allocation) {
        allocations.add(allocation);
    }

    @Override
    public boolean existsByResourceAndDate(Resource resource, LocalDate date) {
        return allocations.stream()
                .anyMatch(a -> a.getResource().getId().equalsIgnoreCase(resource.getId())
                        && a.getDate().equals(date));
    }

    @Override
    public List<Allocation> findByCollaboratorIdAndDate(Collaborator collaborator, LocalDate date) {
        return allocations.stream()
                .filter(a -> a.getCollaborator().getId().equalsIgnoreCase(collaborator.getId())
                        && a.getDate().equals(date))
                .collect(Collectors.toList());
    }
}
