package cepes.itacademy.sarc.domain;

public class Room extends Resource {
    private int seats;
    private boolean projector;

    public Room(String id, int seats, boolean projector) {
        super(id);
        this.seats = seats;
        this.projector = projector;
    }

    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }
    public boolean isProjector() {
        return projector;
    }
    public void setProjector(boolean projector) {
        this.projector = projector;
    }

}
