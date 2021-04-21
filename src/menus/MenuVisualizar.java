package menus;

import java.util.Scanner;
import daos.*;
import util.Render;

public class MenuVisualizar {
  //Garante que todas as tabelas vão retornas todos os campos
  String[] atrs = {}; //Alterar resultará em erro, já que cada tabela possui campos diferentes

  /**
   * Renderiza o menu de escolha das entidades a serem excluidas
   * @param input - um scanner
   */
  public void menuVisualizar(Scanner input) {
    //Se esse valor for false volta para o menu inicial
    boolean continuar = true;

    //Mantem o menu visualizar ativo
    while (continuar) {
      System.out.print(
        "Por favor, escolha de acordo com o número a tabela que deseja exibir:\n" +
        "[0] - Voltar ao menu pricipal\n" + 
        "[1] - Empregado\n" + 
        "[2] - Departamento\n" + 
        "[3] - Dependente\n" + 
        "Resposta: "
      );

      int opcaoEscolhida = Integer.parseInt(input.nextLine());
      System.out.println(Render.renderLine());

      //Posiveis casos
      if (opcaoEscolhida == 0) {
        break;
      } else if (opcaoEscolhida == 1) {
        visualizarEmpregado(input);
        System.out.println(Render.renderLine());
        System.out.println();
      } else if (opcaoEscolhida == 2) {
        visualizarDepartamento(input);
        System.out.println(Render.renderLine());
        System.out.println();
      } else if (opcaoEscolhida == 3) {
        visualizarDependente(input);
        System.out.println(Render.renderLine());
        System.out.println();
      } else {
        System.out.println("Descupe, não conseguimos entender o que você deseja, tente novamente!");
        System.out.println(Render.renderLine());
        System.out.println();
      }
    }
  }

  /**
   * Renderiza a tabela de empregados
   * @param input - um scanner
   */
  public void visualizarEmpregado(Scanner input) {
    try {
      //Imprime a tabela empregados
      EmpregadoDAO.selecionar(atrs, "");
      System.out.print("Aperte enter para continuar...");
      input.nextLine();
    } catch (Exception e) {
      System.out.println("Não foi possivel imprimir a tabela empregado!\n" + e.getMessage() + "\n");
    }
  }

  /**
   * Renderiza a tabela de departamentos
   * @param input - um scanner
   */
  public void visualizarDepartamento(Scanner input) {
    try {
      //Imprime a tabela departamentos
      DepartamentoDAO.selecionar(atrs, "");
      System.out.print("Aperte enter para continuar...");
      input.nextLine();
    } catch (Exception e) {
      System.out.println("Não foi possivel imprimir a tabela departamento!\n" + e.getMessage() + "\n");
    }
  }

  /**
   * Renderiza a tabela de dependentes
   * @param input - um scanner
   */
  public void visualizarDependente(Scanner input) {
    try {
      //Imprime a tabela dependentes
      DependenteDAO.selecionar(atrs, "");
      System.out.print("Aperte enter para continuar...");
      input.nextLine();
    } catch (Exception e) {
      System.out.println("Não foi possivel imprimir a tabela dependente!\n" + e.getMessage() + "\n");
    }
  }
}
