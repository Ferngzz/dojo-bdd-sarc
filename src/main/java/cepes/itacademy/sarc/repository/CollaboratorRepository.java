package cepes.itacademy.sarc.repository;

import cepes.itacademy.sarc.domain.Collaborator;
import java.util.Optional;

public interface CollaboratorRepository {
    Optional<Collaborator> findById(String id);
}
