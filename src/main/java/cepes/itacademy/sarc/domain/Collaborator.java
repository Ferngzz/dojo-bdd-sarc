package cepes.itacademy.sarc.domain;

import java.time.LocalDate;

public class Collaborator {
    private final String id;
    private String name;
    private String role;
    private final LocalDate admission;

    public Collaborator(String id, String name, String role, LocalDate admission){
        this.id = id;
        this.name = name;
        this.role = role;
        this.admission = admission;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getAdmission(){
        return admission;
    }

}
