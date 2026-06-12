package cepes.itacademy.sarc.domain;

public class Lab extends Resource {
    private int computers;
    private String description;

    public Lab(String id, int computers, String description) {
        super(id);
        this.computers = computers;
        this.description = description;
    }

    public int getComputers() {
        return computers;
    }

    public String getDescription() {return description;}

    public void setComputers(int computers) { this.computers = computers;}

    public void setDescription(String description) {this.description = description;}
}

