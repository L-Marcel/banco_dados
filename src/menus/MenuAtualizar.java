package menus;

import java.sql.Connection;
import java.sql.Date;
import java.util.Scanner;

import connections.ConnectionFactory;
import daos.DepartamentoDAO;
import daos.EmpregadoDAO;
import entities.*;

import util.Render;

public class MenuAtualizar {

    public void menuAtualizar(Scanner input) {

        boolean continuar = true;

        while (continuar) {
            System.out.println("Por favor, escolha de acordo com o número a tabela que deseja atualizar:");
            System.out.print("[0] - Voltar ao menu pricipal\n[1] - Empregado\n[2] - Departamento\nResposta: ");

            int opcaoEscolhida = Integer.parseInt(input.nextLine());
            System.out.println(Render.renderLine());

            if (opcaoEscolhida == 0) {
                System.out.println(Render.renderLine());
                break;
            } else if (opcaoEscolhida == 1) {

                atualizarEmpregado(input);

                System.out.println(Render.renderLine());
            } else if (opcaoEscolhida == 2) {

                atualizarDepartamento(input);

                System.out.println(Render.renderLine());
            } else {
                System.out.println("Descupe, não conseguimos entender o que você deseja, tente novamente!");
                System.out.println(Render.renderLine());
            }
        }
    }

    public void atualizarEmpregado(Scanner input) {

        Connection con = new ConnectionFactory().getConnection();
        ConnectionFactory.selectDatabase(con);

        System.out.println("Informe o cpf do empregado: ");
        String cpf = input.nextLine();
        Empregado empregado = EmpregadoDAO.selecionar(con, cpf);
        EmpregadoDAO.atualizar(con, cpf, empregado);

        System.out.println(
                "Informe o campo que deseja alterar.\n[0] - CPF\n[1] - Primeiro nome\n[2] - Último nome\n[3] - Data de nascimento\n[4]- Sálario\n[5] - Endereço\n[6] - Sexo\n[7] - Número do departamento\n[8]- CPF do supervisor");
        int opcaoEscolhida = Integer.parseInt(input.nextLine());
        switch (opcaoEscolhida) {
        case 0:
            System.out.print("Informe o CPF do empregado: ");
            String cpfEmpre = input.nextLine();
            empregado.setCpf(cpfEmpre);
            break;
        case 1:
            System.out.print("Informe o primeiro nome do empregado: ");
            String pnome = input.nextLine();
            empregado.setPnome(pnome);
            break;
        case 2:
            System.out.print("Informe o último nome do empregado: ");
            String unome = input.nextLine();
            empregado.setUnome(unome);

            break;
        case 3:
            System.out.println("Informe a data de nascimento do empregado.");
            System.out.print("Dia: ");
            String dia = input.nextLine();
            System.out.print("Mês: ");
            String mes = input.nextLine();
            System.out.print("Ano: ");
            String ano = input.nextLine();

            String dataNasc = ano + "-" + mes + "-" + dia;
            empregado.setDataNasc(Date.valueOf(dataNasc));

            break;
        case 4:
            System.out.print("Informe o sálario do empregado, (Exemplo: 10000): ");
            Double salario = Double.parseDouble(input.nextLine());
            empregado.setSalario(salario);

            break;
        case 5:
            System.out.print("Informe o endereço do empregado: ");
            String endereco = input.nextLine();
            empregado.setEndereco(endereco);

            break;
        case 6:
            System.out.print("Informe o sexo do empregado (F, M ou O): ");
            String sexo = input.nextLine();
            empregado.setSexo(sexo);
            break;
        case 7:
            System.out.print("Informe o numero do departamento: ");
            int numDep = Integer.parseInt(input.nextLine());
            empregado.setNumeroDep(numDep);
            break;
        case 8:
            System.out.print("Informe o cpf do supervisor: ");
            String cpfSupervisor = input.nextLine();
            empregado.setCpfSupervisor(cpfSupervisor);
            break;

        }
        EmpregadoDAO.atualizar(con, cpf, empregado);

    }

    public void atualizarDepartamento(Scanner input) {

        Connection con = new ConnectionFactory().getConnection();
        ConnectionFactory.selectDatabase(con);

        System.out.println("Informe o número do departamento: ");
        int numDep = Integer.parseInt(input.nextLine());
        Departamento departamento = DepartamentoDAO.selecionar(con, numDep);
        DepartamentoDAO.atualizar(con, numDep, departamento);
        
        System.out.println(
                "Informe o campo que deseja alterar.\n[0] - Numero do departamento\n[1] - Nome do departamento\n[2] - Cpf do gerente\n[3] - Data de inicio do gerente");

        int opcaoEscolhida = Integer.parseInt(input.nextLine());
        switch (opcaoEscolhida) {
        case 0:
            System.out.print("Informe o número do departamento: ");
            int numeroDep = Integer.parseInt(input.nextLine());
            departamento.setNumero(numeroDep);
            break;
        case 1:
            System.out.print("Informe o nome do departamento: ");
            String nomeDep = input.nextLine();
            departamento.setNome(nomeDep);
            break;
        case 2:
            System.out.print("Informe o CPF do gerente: ");
            String cpfGerente = input.nextLine();
            departamento.setCpfGerente(cpfGerente);
            break;
        case 3:
            System.out.println("Informe a data de inicio do gerente.");
            System.out.print("Dia: ");
            String dia = input.nextLine();
            System.out.print("Mês: ");
            String mes = input.nextLine();
            System.out.print("Ano: ");
            String ano = input.nextLine();

            String dataInicioGerente = ano + "-" + mes + "-" + dia;

            departamento.setDataIniGenrente(Date.valueOf(dataInicioGerente));
            break;
        }
        DepartamentoDAO.atualizar(con, numDep, departamento);
    }

}
