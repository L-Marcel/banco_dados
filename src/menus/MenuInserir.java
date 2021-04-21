package menus;

import java.util.Scanner;
import daos.*;
import entities.*;
import util.Render;

public class MenuInserir {
    String[] atrsDepartamento = { "numero", "nome" };
    String[] atrsEmpregado = { "cpf", "pnome", "unome", "numero_dep" };
    String[] atrsDependente = { "nome", "cpf_empregado", "parentesco" };

    public void menuInserir(Scanner input) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("Por favor, escolha de acordo com o número a tabela que deseja inserir dados:");
            System.out.print(
                    "[0] - Voltar ao menu pricipal\n[1] - Empregado\n[2] - Departamento\n[3] - Dependente\nResposta: ");

            int opcaoEscolhida = Integer.parseInt(input.nextLine());
            System.out.println(Render.renderLine());

            if (opcaoEscolhida == 0) {
                System.out.println(Render.renderLine());
                break;
            } else if (opcaoEscolhida == 1) {
                inserirEmpregado(input);
                System.out.println(Render.renderLine());
            } else if (opcaoEscolhida == 2) {
                inserirDepartamento(input);
                System.out.println(Render.renderLine());
            } else if (opcaoEscolhida == 3) {
                inserirDependente(input);
                System.out.println(Render.renderLine());
            } else {
                System.out.println("Descupe, não conseguimos entender o que você deseja, tente novamente!");
                System.out.println(Render.renderLine());
            }
        }

    }

    public void inserirEmpregado(Scanner input) {
        try {      
            EmpregadoDAO.selecionar(atrsEmpregado, "");

            System.out.print("Informe o CPF do novo empregado: ");
            String cpfEmpre = input.nextLine();

            System.out.print("Informe o primeiro nome do empregado: ");
            String pnome = input.nextLine();

            System.out.print("Informe o último nome do empregado: ");
            String unome = input.nextLine();

            System.out.print("Informe o endereço do empregado: ");
            String endereco = input.nextLine();

            System.out.print("Informe o sálario do empregado, (Exemplo: 10000): ");
            Double salario = Double.parseDouble(input.nextLine());

            System.out.println("Informe a data de nascimento do empregado.");
            System.out.print("Dia: ");
            String dia = input.nextLine();
            System.out.print("Mês: ");
            String mes = input.nextLine();
            System.out.print("Ano: ");
            String ano = input.nextLine();

            String dataNasc = ano + "-" + mes + "-" + dia;

            System.out.print("Informe o sexo do empregado (F, M ou O): ");
            String sexo = input.nextLine();

            DepartamentoDAO.selecionar(atrsDepartamento, "");


            System.out.print("Informe o numero do departamento: ");
            int numDep = Integer.parseInt(input.nextLine());

            Empregado empregado = new Empregado(cpfEmpre, pnome, unome, dataNasc, endereco, salario, sexo, numDep);

            System.out.print("O empregado possui supervisor?\n[0] - Não\n[1] - Sim\nResposta: ");
            int possuiSupervisor = Integer.parseInt(input.nextLine());

            if (possuiSupervisor == 1) {
                EmpregadoDAO.selecionar(atrsEmpregado, "");

                System.out.print("Informe o cpf do supervisor: ");
                String cpfSupervirsor = input.nextLine();

                empregado.setCpfSupervisor(cpfSupervirsor);
            }

            System.out.print(
                    "Por favor, confirme se deseja inserir o empregado:\n[0] - Desejo inserir\n[1] - Cancelar\nResposta: ");
            int confirmacao = Integer.parseInt(input.nextLine());

            if (confirmacao == 0) {
                System.out.println(Render.renderLine() + "\n");
                EmpregadoDAO.adicionar(empregado);
            }
        } catch (Exception e) {
            System.out.println("Não foi possivel inserir empregado.");
            System.out.println(e.getMessage());
        }
    }

    public void inserirDepartamento(Scanner input) {
        System.out.print("Informe o numero do departamento: ");
        int numDep = Integer.parseInt(input.nextLine());

        EmpregadoDAO.selecionar(atrsEmpregado, "");

        System.out.print("Informe o cpf do gerente: ");
        String cpfGerente = input.nextLine();
        System.out.print("Informe o nome do departamento: ");
        String nome = input.nextLine();

        System.out.println("Informe a data de inicio do gerente");
        System.out.print("Dia:");
        String dia = input.nextLine();
        System.out.print("Mês:");
        String mes = input.nextLine();
        System.out.print("Ano:");
        String ano = input.nextLine();

        String dataInicio = ano + "-" + mes + "-" + dia;

        System.out.print(
                "Por favor, confirme se deseja inserir o departamento:\n[0] - Desejo inserir\n[1] - Cancelar\nResposta: ");
        int confirmacao = Integer.parseInt(input.nextLine());

        if (confirmacao == 0) {
            try {
                System.out.println(Render.renderLine() + "\n");
                Departamento dep = new Departamento(numDep, nome, cpfGerente, dataInicio);
                DepartamentoDAO.adicionar(dep);
            } catch (Exception ex) {
                System.out.println("Não foi possivel inserir departamento.\n" + ex.getMessage());
            }
        }
    }

    public void inserirDependente(Scanner input) {
        DependenteDAO.selecionar(atrsDependente, "");

        System.out.print("Informe o nome do dependente: ");
        String nome = input.nextLine();

        System.out.print("Informe o cpf do empregado: ");
        String cpfEmpregado = input.nextLine();

        System.out.print("Informe o sexo do empregado: ");
        String sexo = input.nextLine();
        System.out.print("Informe o parentesco com o empregado: ");
        String parentesco = input.nextLine();

        System.out.println("Informe a data de nascimento do dependente.");
        System.out.print("Dia:");
        String dia = input.nextLine();
        System.out.print("Mês:");
        String mes = input.nextLine();
        System.out.print("Ano:");
        String ano = input.nextLine();

        String dataNasc = ano + "-" + mes + "-" + dia;

        System.out.print(
                "Por favor, confirme se deseja inserir o dependente:\n[0] - Desejo inserir\n[1] - Cancelar\nResposta: ");
        int confirmacao = Integer.parseInt(input.nextLine());

        if (confirmacao == 0) {
            try {
                System.out.println(Render.renderLine() + "\n");
                Dependente dependente = new Dependente(nome, cpfEmpregado, dataNasc, sexo, parentesco);
                DependenteDAO.adicionar(dependente);
            } catch (Exception ex) {
                System.out.println("Não foi possivel inserir o dependente.\n" + ex.getMessage());
            }
        }
    }

}