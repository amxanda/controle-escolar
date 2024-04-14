package model;

public abstract class Pessoa {

    private String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override // Dados da pessoa
    public String toString() {
        return "Nome: " + getNome();
    }
}
