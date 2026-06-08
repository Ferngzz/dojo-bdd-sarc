package cepes.itacademy.sarc.domain;

import java.time.LocalDate;

public class Alocacao {
    private final Funcionario funcionario;
    private final Recurso recurso;
    private final LocalDate data;

    public Alocacao(Funcionario funcionario, Recurso recurso, LocalDate data) {
        this.funcionario = funcionario;
        this.recurso = recurso;
        this.data = data;
    }

    public Funcionario getFuncionario() {return funcionario;}
    public Recurso getRecurso() {return recurso;}
    public LocalDate getData() {return data;}
}
