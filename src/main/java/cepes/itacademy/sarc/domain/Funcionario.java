package cepes.itacademy.sarc.domain;

import java.time.LocalDate;

public class Funcionario {
    private final String matricula;
    private String nome;
    private String cargo;
    private final LocalDate admissao;

    public Funcionario(String matricula, String nome, String cargo, LocalDate admissao){
        this.matricula = matricula;
        this.nome = nome;
        this.cargo = cargo;
        this.admissao = admissao;

    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo(){
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getAdmissao(){
        return admissao;
    }

}
