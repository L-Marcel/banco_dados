package menus;

import java.sql.Connection;
import java.util.Scanner;

import daos.DepartamentoDAO;
import daos.EmpregadoDAO;
import util.Render;

public class MenuVisualizar {

    String[] atrs = {};

    public void menuVisualizar(Scanner input, Connection con) {

        boolean continuar = true;

        while (continuar) {

            Menu menuPrincipal = new Menu();

            System.out.println("Por favor, escolha de acordo com o número a tabela que deseja exibir:");
            System.out.println("[0] - Voltar ao menu pricipal\n[1] - Empregado\n[2] - Departamento");

            int opcaoEscolhida = Integer.parseInt(input.nextLine());
            System.out.println(Render.renderLine());

            if (opcaoEscolhida == 0) {

                menuPrincipal.menu(input, con);
                System.out.println(Render.renderLine());

            }
            if (opcaoEscolhida == 1) {
                try {
                    visualizarEmpregado(con);
                } catch (Exception e) {
                    System.out.println("Não foi possivel imprimir a tabela empregado!\n" + e.getMessage() + "\n");
                }
                System.out.println(Render.renderLine());
            }
            if (opcaoEscolhida == 2) {
                try {
                    visualizarDepartamento(con);
                } catch (Exception e) {
                    System.out.println("Não foi possivel imprimir a tabela departamento!\n" + e.getMessage() + "\n");
                }
                System.out.println(Render.renderLine());
            }
            if ((opcaoEscolhida != 0) && (opcaoEscolhida != 1) && (opcaoEscolhida != 2)) {
                System.out.println("Descupe, não conseguimos entender o que você deseja, tente novamente!");
                System.out.println(Render.renderLine());
            }
        }
    }

    public void visualizarEmpregado(Connection con) {
        EmpregadoDAO.selecionar(con, atrs, "");
    }

    public void visualizarDepartamento(Connection con) {
        DepartamentoDAO.selecionar(con, atrs, "");
    }

}
