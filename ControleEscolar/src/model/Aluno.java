package model;

public class Aluno extends Pessoa implements Cadastro {

    private String periodo;
    private String matricula;
    private Turma turma;

    public Aluno(String nome, String periodo, String matricula) {
        super(nome);
        this.periodo = periodo;
        this.matricula = matricula;
    }

    // Getters e Setters
    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
    
    @Override
    public boolean verificarCadastro(String nome) {
        return this.getNome().equalsIgnoreCase(nome);
    }

    @Override
    public String toString() {
        return super.toString() + ", Período: " + getPeriodo() + ", Matrícula: " + getMatricula() + ", Turma: " + (turma != null ? turma.getCodigo() : "Nulo") + ", Disciplina: " + (turma != null ? turma.getDisciplina() : "Nulo") + ", Professor Responsável: " + (turma != null && turma.getProfessor() != null ? turma.getProfessor().getNome() : "Nulo");
    }
}
