package menus;

import java.sql.Connection;
import java.util.Scanner;

import daos.EmpregadoDAO;
import entities.Empregado;

public class MenuInserir {

    public void menuInserir(Scanner input, Connection con) {
        System.out.println("Por favor, escolha de acordo com o número a tabela que deseja inserir dados:");
        System.out.println("[0] - Voltar ao menu pricipal\n[1] - Empregado\n[2] - Departamento");
        int opcaoEscolhida = Integer.parseInt(input.nextLine());
        traco();

        if (opcaoEscolhida == 0) {
            Menu menuPrincipal = new Menu();
            menuPrincipal.menu(input, con);

        } else if (opcaoEscolhida == 1) {
            inserirEmpregado(input, con);
            traco();

        } else if (opcaoEscolhida == 2) {
            System.out.println("Desculpe, ainda não é possivel inserir nessa tabela!");

        } else {
            System.out.println("Descupe, não conseguimos entender o que você deseja, tente novamente!");
            traco();
        }

    }

    public void traco() {
        System.out.println("---------------------------------------");
    }

    public void inserirEmpregado(Scanner input, Connection con) {

        System.out.println("Informe o CPF do empregado: ");
        String cpfEmpre = input.nextLine();

        System.out.println("Informe o primeiro nome do empregado: ");
        String pnome = input.nextLine();

        System.out.println("Informe o último nome do empregado: ");
        String unome = input.nextLine();

        System.out.println("Informe o endereço do empregado: ");
        String endereco = input.nextLine();

        System.out.println("Informe o sálario do empregado, (Exemplo: 10000):");
        Double salario = Double.parseDouble(input.nextLine());

        System.out.println("Informe a data de nascimento do empregado.");
        System.out.println("Dia:");
        String dia = input.nextLine();
        System.out.println("Mês:");
        String mes = input.nextLine();
        System.out.println("Ano:");
        String ano = input.nextLine();

        String dataNasc = ano + "-" + mes + "-" + dia;

        System.out.println("Informe o sexo do empregado (F ou M):");
        String sexo = input.nextLine();

        System.out.println("Informe o numero do departamento:");
        int numDep = Integer.parseInt(input.nextLine());

        System.out.println("O empregado possui supervisor?\n[0] - Não\n[1] - Sim");
        int possuiSupervisor = Integer.parseInt(input.nextLine());
        try {

            if (possuiSupervisor == 0) {
                Empregado empregado = new Empregado(cpfEmpre, pnome, unome, dataNasc, endereco, salario, sexo, numDep);
                EmpregadoDAO.adicionar(con, empregado);
            } else {
                System.out.println("Informe o cpf do supervisor:");
                String cpfSupervirsor = input.nextLine();
                Empregado empregado = new Empregado(cpfEmpre, pnome, unome, dataNasc, endereco, salario, sexo, numDep,
                        cpfSupervirsor);
                EmpregadoDAO.adicionar(con, empregado);

            }
            System.out.println("Empregado inserido com sucesso!");

        } catch (Exception e) {
            System.out.println("Não foi possivel inserir empregado.");
            System.out.println(e.getMessage());

        }

    }
}
