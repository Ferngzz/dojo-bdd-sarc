package cepes.itacademy.sarc.domain;

public class Laboratorio extends  Recurso{
    private int computadores;
    private String descricao;

    public Laboratorio(String nome, int computadores, String descricao) {
        super(nome);
        this.computadores = computadores;
        this.descricao = descricao;
    }

    public int getComputadores() {
        return computadores;
    }

    public String getDescricao() {return descricao;}

    public void setComputadores(int computadores) { this.computadores = computadores;}

    public void setDescricao(String descricao) {this.descricao = descricao;}
}

