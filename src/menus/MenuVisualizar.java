package menus;

import java.util.Scanner;
import daos.*;
import util.Render;

public class MenuVisualizar {
    String[] atrs = {};

    public void menuVisualizar(Scanner input) {

        boolean continuar = true;

        while (continuar) {
            System.out.println("Por favor, escolha de acordo com o número a tabela que deseja exibir:");
            System.out.print(
                    "[0] - Voltar ao menu pricipal\n[1] - Empregado\n[2] - Departamento\n[3] - Dependente\nResposta: ");

            int opcaoEscolhida = Integer.parseInt(input.nextLine());
            System.out.println(Render.renderLine());

            if (opcaoEscolhida == 0) {
                break;
            } else if (opcaoEscolhida == 1) {
                visualizarEmpregado(input);
                System.out.println(Render.renderLine());
            } else if (opcaoEscolhida == 2) {
                visualizarDepartamento(input);
                System.out.println(Render.renderLine());
            } else if (opcaoEscolhida == 3) {
                visualizarDependente(input);
                System.out.println(Render.renderLine());
            } else {
                System.out.println("Descupe, não conseguimos entender o que você deseja, tente novamente!");
                System.out.println(Render.renderLine());
            }
        }
    }

    public void visualizarEmpregado(Scanner input) {
        try {
            EmpregadoDAO.selecionar(atrs, "");
            System.out.print("Aperte enter para continuar...");
            input.nextLine();
        } catch (Exception e) {
            System.out.println("Não foi possivel imprimir a tabela empregado!\n" + e.getMessage() + "\n");
        }
    }

    public void visualizarDepartamento(Scanner input) {
        try {
            DepartamentoDAO.selecionar(atrs, "");
            System.out.print("Aperte enter para continuar...");
            input.nextLine();
        } catch (Exception e) {
            System.out.println("Não foi possivel imprimir a tabela departamento!\n" + e.getMessage() + "\n");
        }
    }

    public void visualizarDependente(Scanner input) {
        try {
            DependenteDAO.selecionar(atrs, "");
            System.out.print("Aperte enter para continuar...");
            input.nextLine();
        } catch (Exception e) {
            System.out.println("Não foi possivel imprimir a tabela dependente!\n" + e.getMessage() + "\n");
        }
    }
}
