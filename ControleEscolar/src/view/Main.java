package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Aluno;
import model.Cadastro;
import model.Professor;
import model.Turma;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static List<Cadastro> cadastros = new ArrayList<>();
    private static List<Turma> turmas = new ArrayList<>();

    public static void main(String[] args) {
        exibirMenu();
    }

    private static void exibirMenu() {
        int opcao;
        do {
            // Menu principal
            System.out.println("\n----- MENU -----");
            System.out.println("1. Cadastrar Professor");
            System.out.println("2. Cadastrar Turma");
            System.out.println("3. Cadastrar Aluno");
            System.out.println("4. Procurar Cadastro");
            System.out.println("5. Exibir Alunos Cadastrados");
            System.out.println("6. Exibir Professores Cadastrados");
            System.out.println("7. Exibir Disciplina Cadastradas");
            System.out.println("8. Exibir Todos os Cadastros");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");

            // Ler a entrada do usuário como uma string
            String opcaoStr = scanner.nextLine();

            // Converter a string para um inteiro
            opcao = Integer.parseInt(opcaoStr);

            switch (opcao) {
                case 1:
                    cadastrarProfessor();
                    break;
                case 2:
                    cadastrarTurma();
                    break;
                case 3:
                    cadastrarAluno();
                    break;
                case 4:
                    procurarCadastro();
                    break;
                case 5:
                    exibirAlunosCadastrados();
                    break;
                case 6:
                    exibirProfessoresCadastrados();
                    break;
                case 7:
                    exibirDisciplinasCadastradas();
                    break;
                case 8:
                    exibirTodosCadastrados();
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 9);
    }

    private static void cadastrarProfessor() {
        System.out.println("\n----- CADASTRO DE PROFESSOR -----");
        System.out.println("Digite o nome do professor:");
        String nomeProfessor = scanner.nextLine();

        System.out.println("Digite o registro do professor:");
        String registroProfessor = scanner.nextLine();

        Professor professor = new Professor(nomeProfessor, registroProfessor);
        cadastros.add(professor);
        System.out.println("Professor cadastrado com sucesso: " + professor);
    }

    private static void cadastrarTurma() {
        System.out.println("\n----- CADASTRO DE TURMA -----");
        System.out.println("Digite o código da turma:");
        String codigoTurma = scanner.nextLine();

        System.out.println("Digite o nome do professor:");
        String nomeProfessor = scanner.nextLine();

        System.out.println("Digite o nome da disciplina:");
        String disciplina = scanner.nextLine();

        Professor professor = buscarProfessor(nomeProfessor);

        Turma turma = new Turma(codigoTurma, professor, disciplina);
        turmas.add(turma);
        System.out.println("Turma cadastrada com sucesso: " + turma);
    }

    private static Turma buscarTurma(String codigoTurma) {
        for (Turma turma : turmas) {
            if (turma.getCodigo().equals(codigoTurma)) {
                return turma;
            }
        }
        return null;
    }

    private static void cadastrarAluno() {
        System.out.println("\n----- CADASTRO DE ALUNO -----");
        System.out.println("Digite o nome do aluno:");
        String nomeAluno = scanner.nextLine();

        System.out.println("Digite o período do aluno:");
        String periodoAluno = scanner.nextLine();

        System.out.println("Digite a matrícula do aluno:");
        String matriculaAluno = scanner.nextLine();

        System.out.println("Digite o código da turma:");
        String codigoTurma = scanner.nextLine();

        Turma turma = buscarTurma(codigoTurma);

        if (turma != null) {
            Aluno aluno = new Aluno(nomeAluno, periodoAluno, matriculaAluno);
            aluno.setTurma(turma); // Associar o aluno à turma
            turma.matricularAluno(aluno);
            cadastros.add(aluno);
            System.out.println("Aluno cadastrado com sucesso na turma " + codigoTurma + ": " + aluno);
        } else {
            System.out.println("Turma não encontrada para o código " + codigoTurma);
        }
    }

    private static Professor buscarProfessor(String registroProfessor) {
        for (Cadastro cadastro : cadastros) {
            if (cadastro.verificarCadastro(registroProfessor)) {
                return (Professor) cadastro;
            }
        }
        return null;
    }

    private static Aluno buscarAluno(String matriculaAluno) {
        for (Cadastro cadastro : cadastros) {
            if (cadastro.verificarCadastro(matriculaAluno)) {
                return (Aluno) cadastro;
            }
        }
        return null;
    }

    private static void procurarCadastro() {
        System.out.println("\n----- PROCURAR CADASTRO -----");
        System.out.println("Digite o nome para procurar:");
        String nome = scanner.nextLine();

        boolean encontrado = false;
        for (Cadastro cadastro : cadastros) {
            if (cadastro.verificarCadastro(nome)) {
                if (cadastro instanceof Aluno) {
                    Aluno aluno = (Aluno) cadastro;
                    System.out.println(nome + " está cadastrado como aluno:");
                    Turma turma = aluno.getTurma();
                    if (turma != null) {
                        System.out.println("Nome: " + aluno.getNome() + ", Período: " + aluno.getPeriodo() + ", Matrícula: " + aluno.getMatricula() + ", Turma: " + turma.getCodigo() + ", Disciplina: " + turma.getDisciplina() + ", Professor Responsável: " + (turma.getProfessor() != null ? turma.getProfessor().getNome() : "Nulo"));
                    } else {
                        System.out.println("Turma: Nulo, Disciplina: Nulo, Professor Responsável: Nulo");
                    }
                } else if (cadastro instanceof Professor) {
                    Professor professor = (Professor) cadastro;
                    System.out.println(nome + " está cadastrado como professor:");
                    System.out.println("Nome: " + professor.getNome() + ", Registro: " + professor.getRegistro());
                }
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Cadastro não encontrado para " + nome);
        }
    }

    private static void exibirAlunosCadastrados() {
        System.out.println("\n----- ALUNOS CADASTRADOS -----");
        for (Cadastro cadastro : cadastros) {
            if (cadastro instanceof Aluno) {
                Aluno aluno = (Aluno) cadastro;
                Turma turma = aluno.getTurma();
                if (turma != null) {
                    System.out.println("Nome: " + aluno.getNome() + ", Período: " + aluno.getPeriodo() + ", Matrícula: " + aluno.getMatricula() + ", Turma: " + turma.getCodigo() + ", Disciplina: " + turma.getDisciplina() + ", Professor Responsável: " + (turma.getProfessor() != null ? turma.getProfessor().getNome() : "Nulo"));
                } else {
                    System.out.println("Nome: " + aluno.getNome() + ", Período: " + aluno.getPeriodo() + ", Matrícula: " + aluno.getMatricula() + ", Turma: Nulo, Disciplina: Nulo, Professor Responsável: Nulo");
                }
            }
        }
    }

    private static void exibirProfessoresCadastrados() {
        System.out.println("\n----- PROFESSORES CADASTRADOS -----");
        for (Cadastro cadastro : cadastros) {
            if (cadastro instanceof Professor) {
                Professor professor = (Professor) cadastro;
                System.out.println("Nome: " + professor.getNome() + ", Registro: " + professor.getRegistro());
            }
        }
    }

    private static void exibirDisciplinasCadastradas() {
        System.out.println("\n----- DISCIPLINAS CADASTRADAS -----");
        for (Turma turma : turmas) {
            System.out.println("Código: " + turma.getCodigo() + ", Disciplina: " + turma.getDisciplina() + ", Professor Responsável: " + (turma.getProfessor() != null ? turma.getProfessor().getNome() : "Nulo") + ", Número de Alunos: " + turma.quantidadeAlunos());
        }
    }

    private static void exibirTodosCadastrados() {
        System.out.println("\n----- TODOS OS CADASTRADOS -----");
        for (Cadastro cadastro : cadastros) {
            if (cadastro instanceof Aluno) {
                Aluno aluno = (Aluno) cadastro;
                System.out.println("Nome: " + aluno.getNome() + ", Período: " + aluno.getPeriodo() + ", Matrícula: " + aluno.getMatricula());
                Turma turma = aluno.getTurma();
                if (turma != null) {
                    System.out.println("Turma: " + turma.getCodigo() + ", Disciplina: " + turma.getDisciplina() + ", Professor Responsável: " + (turma.getProfessor() != null ? turma.getProfessor().getNome() : "Nulo"));
                } else {
                    System.out.println("Turma: Nulo, Disciplina: Nulo, Professor Responsável: Nulo");
                }
            } else if (cadastro instanceof Professor) {
                Professor professor = (Professor) cadastro;
                System.out.println("Nome: " + professor.getNome() + ", Registro: " + professor.getRegistro());
            }
        }
    }
}
