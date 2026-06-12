package cepes.itacademy.sarc.domain;

import java.time.LocalDate;

public class Allocation {
    private final Collaborator collaborator;
    private final Resource resource;
    private final LocalDate date;

    public Allocation(Collaborator collaborator, Resource resource, LocalDate date) {
        this.collaborator = collaborator;
        this.resource = resource;
        this.date = date;
    }

    public Collaborator getCollaborator() {return collaborator;}
    public Resource getResource() {return resource;}
    public LocalDate getDate() {return date;}
}
