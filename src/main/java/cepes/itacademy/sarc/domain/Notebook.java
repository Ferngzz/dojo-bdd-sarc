package cepes.itacademy.sarc.domain;

import java.time.LocalDate;

public class Notebook extends Resource {

    private final LocalDate acquisition;
    private String description;

    public Notebook(String id, LocalDate acquisition, String description) {
        super(id);
        this.acquisition = acquisition;
        this.description = description;
    }

    public LocalDate getAcquisition() {return acquisition;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}
}
