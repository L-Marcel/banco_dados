package menus;

import java.sql.Connection;
import java.util.Scanner;

import connections.ConnectionFactory;
import daos.*;
import util.Render;

public class MenuExcluir {
    public void menuExcluir(Scanner input) {

        boolean continuar = true;

        while (continuar) {
            System.out.println("Por favor, escolha de acordo com o número a tabela:");
            System.out.print("[0] - Voltar ao menu pricipal\n[1] - Empregado\n[2] - Departamento\nResposta: ");

            int opcaoEscolhida = Integer.parseInt(input.nextLine());
            System.out.println(Render.renderLine());

            if (opcaoEscolhida == 0) {
                System.out.println(Render.renderLine());
                break;
            } else if (opcaoEscolhida == 1) {
                excluirEmpregado(input);
                System.out.println(Render.renderLine());
            } else if (opcaoEscolhida == 2) {
                excluirDepartamento(input);
                System.out.println(Render.renderLine());
            } else {
                System.out.println("Descupe, não conseguimos entender o que você deseja, tente novamente!");
                System.out.println(Render.renderLine());
            }
        }
    }

    public void excluirEmpregado(Scanner input) {

        try {
            Connection con = new ConnectionFactory().getConnection();
            ConnectionFactory.selectDatabase(con);

            System.out.println("Informe o cpf do empregado que deseja excluir: ");
            String cpf = input.nextLine();
            EmpregadoDAO.remover(con, cpf);
            con.close();

        } catch (Exception e) {
            System.out.println("Não foi possivel excluir o empregado." + e);
        }

    }

    public void excluirDepartamento(Scanner input) {
        try {
            Connection con = new ConnectionFactory().getConnection();
            ConnectionFactory.selectDatabase(con);

            System.out.println("Informe o numero do departamento que deseja excluir: ");
            int numero = Integer.parseInt(input.nextLine());
            DepartamentoDAO.remover(con, numero);

            con.close();
        } catch (Exception e) {
            System.out.println("Não foi possivel excluir o departamento." + e);
        }

    }
    // public void excluirDependente(Scanner input){
    //     try {
    //         Connection con = new ConnectionFactory().getConnection();
    //         ConnectionFactory.selectDatabase(con);

    //         System.out.println("Informe o cpf do empregado do dependente que deseja excluir: ");
    //         String cpf = input.nextLine();
    //         DependenteDAO.remover(con, cpfEmpregado)

    //         con.close();
    //     } catch (Exception e) {
    //         System.out.println("Não foi possivel excluir o departamento." + e);
    //     }

    // }
}
