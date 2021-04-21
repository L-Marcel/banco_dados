package menus;

import java.util.Scanner;
import daos.*;
import util.Render;

public class MenuExcluir {
    String[] atrsDepartamento = { "numero", "nome" };
    String[] atrsEmpregado = { "cpf", "pnome", "unome" };
    String[] atrsDependente = { "nome", "cpf_empregado", "parentesco" };

    public void menuExcluir(Scanner input) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("Por favor, escolha de acordo com o número a tabela:");
            System.out.print("[0] - Voltar ao menu pricipal\n[1] - Empregado\n[2] - Departamento\n[3] - Dependente\nResposta: ");

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
            } else if (opcaoEscolhida == 3) {
                excluirDependente(input);
                System.out.println(Render.renderLine());
            }else {
                System.out.println("Descupe, não conseguimos entender o que você deseja, tente novamente!");
                System.out.println(Render.renderLine());
            }
        }
    }

    public void excluirEmpregado(Scanner input) {
        try {
            EmpregadoDAO.selecionar(atrsEmpregado, "");
            System.out.println("Informe o cpf do empregado que deseja excluir: ");
            String cpf = input.nextLine();
            EmpregadoDAO.remover(cpf);
        } catch (Exception e) {
            System.out.println("Não foi possivel excluir o empregado." + e);
        }

    }

    public void excluirDepartamento(Scanner input) {
        try {
            DepartamentoDAO.selecionar(atrsDepartamento, "");
            System.out.print("Informe o numero do departamento que deseja excluir: ");
            int numero = Integer.parseInt(input.nextLine());
            DepartamentoDAO.remover(numero);
        } catch (Exception e) {
            System.out.println("Não foi possivel excluir o departamento." + e);
        }

    }
    public void excluirDependente(Scanner input){
        try {
            DependenteDAO.selecionar(atrsDependente, "");
            System.out.print("Informe o cpf do empregado do dependente que deseja excluir: ");
            String cpf = input.nextLine();
            System.out.print("Informe o nome do dependente que deseja excluir: ");
            String nome = input.nextLine();
            DependenteDAO.remover(cpf, nome);
        } catch (Exception e) {
            System.out.println("Não foi possivel excluir o dependente." + e);
        }

    }
}
