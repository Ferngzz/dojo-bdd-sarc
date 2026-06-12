package cepes.itacademy.sarc.repository;

import cepes.itacademy.sarc.domain.Collaborator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryCollaboratorRepository implements CollaboratorRepository {
    private final List<Collaborator> collaborators = new ArrayList<>();

    public InMemoryCollaboratorRepository() {
        collaborators.add(new Collaborator("10002020", "Andre Oliveira", "Analista Administrativo", LocalDate.of(2020, 1, 1)));
        collaborators.add(new Collaborator("20002026", "Gabriel Velloso", "Aluno de Mestrado", LocalDate.of(2025, 6, 12)));
        collaborators.add(new Collaborator("20002024", "Natalya Goelzer", "Aluna de Doutorado", LocalDate.of(2024, 3, 1)));
    }

    @Override
    public Optional<Collaborator> findById(String id) {
        return collaborators.stream()
                .filter(f -> f.getId().equalsIgnoreCase(id))
                .findFirst();
    }
}
