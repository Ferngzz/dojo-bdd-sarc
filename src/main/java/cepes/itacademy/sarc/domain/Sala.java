package cepes.itacademy.sarc.domain;

public class Sala extends Recurso{
    private int lugares;
    private boolean projetor;

    public Sala(String numero, int lugares, boolean projetor) {
        super(numero);
        this.lugares = lugares;
        this.projetor = projetor;
    }

    public int getLugares() {
        return lugares;
    }
    public void setLugares(int lugares) {
        this.lugares = lugares;
    }
    public boolean isProjetor() {
        return projetor;
    }
    public void setProjetor(boolean projetor) {
        this.projetor = projetor;
    }

}
