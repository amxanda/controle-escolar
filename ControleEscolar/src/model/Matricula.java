package model;

public class Matricula {

    private Aluno aluno;
    private Turma turma;

    public Matricula(Aluno aluno, Turma turma) {
        this.aluno = aluno;
        this.turma = turma;
    }

    // Getters e Setters
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
}
