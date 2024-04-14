package model;

public class Professor extends Pessoa implements Cadastro {

    private String registro;

    public Professor(String nome, String registro) {
        super(nome);
        this.registro = registro;
    }

    // Getters e Setters
    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    @Override
    public boolean verificarCadastro(String nome) {
        return this.getNome().equalsIgnoreCase(nome);
    }

    @Override
    public String toString() {
        return super.toString() + ", Registro: " + getRegistro();
    }
}
