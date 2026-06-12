package cepes.itacademy.sarc.domain;

public abstract class Resource {
    private final String id;

    public Resource(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
