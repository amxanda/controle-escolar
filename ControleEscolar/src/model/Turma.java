package model;

import java.util.ArrayList;
import java.util.List;

public class Turma {

    private String codigo;
    private String disciplina;
    private List<Aluno> alunos;
    private List<Matricula> matriculas;
    private Professor professor;

    public Turma(String codigo, Professor professor, String disciplina) {
        this.codigo = codigo;
        this.professor = professor;
        this.disciplina = disciplina;
        this.alunos = new ArrayList<>();
        this.matriculas = new ArrayList<>();
    }

    // Getters e Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void adicionarAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    // cria a matricula do aluno na turma
    public void matricularAluno(Aluno aluno) {
        Matricula matricula = new Matricula(aluno, this);
        this.matriculas.add(matricula);
        this.alunos.add(aluno);
    }

    // verifica a quantidade de alunos na turma
    public int quantidadeAlunos() {
        return alunos.size();
    }

    @Override
    public String toString() { //Dados da 
        return "Código: " + codigo + ", Disciplina: " + disciplina + ", Professor: " + (professor != null ? professor.getNome() : "Nulo") + ", Número de Alunos: " + alunos.size();
    }
}
