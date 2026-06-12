package cepes.itacademy.sarc.repository;

import cepes.itacademy.sarc.domain.Allocation;
import cepes.itacademy.sarc.domain.Collaborator;
import cepes.itacademy.sarc.domain.Resource;

import java.time.LocalDate;
import java.util.List;

public interface AllocationRepository {
    void saveAllocation(Allocation allocation);

    boolean existsByResourceAndDate(Resource resource, LocalDate date);

    List<Allocation> findByCollaboratorIdAndDate(Collaborator collaborator, LocalDate date);

}
