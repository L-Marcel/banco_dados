package menus;

import java.util.Scanner;
import daos.*;
import util.Render;

public class MenuExcluir {
  /**
   * Renderiza o menu de escolha das entidades a serem excluidas
   * @param input - um scanner
   */
  public void menuExcluir(Scanner input) {
    //Se esse valor for false volta para o menu inicial
    boolean continuar = true;

    //Mantem o menu excluir ativo
    while (continuar) {
      System.out.print(
        "Por favor, escolha de acordo com o número a tabela:\n" +
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

  /**
   * Renderiza o menu de excluir empregado
   * @param input - um scanner
   */
  public void excluirEmpregado(Scanner input) {
    //Previne erro
    try {
      //Imprime a tabela de empregados para o usuário saber qual o cpf
      //do empregado que ele quer excluir
      EmpregadoDAO.selecionar(Menu.atrsEmpregado, "");

      System.out.print("Informe o cpf do empregado que deseja excluir: ");
      String cpf = input.nextLine();
      System.out.println(Render.renderLine());
      System.out.print(
        "Por favor, confirme se deseja excluir o empregado:\n" + 
        "[0] - CONFIRMAR\n" + 
        "[1] - CANCELAR\n" + 
        "Resposta: "
      );

      int confirmacao = Integer.parseInt(input.nextLine());

      if (confirmacao == 0) {
        //Exclusão confirmada
        EmpregadoDAO.remover(cpf);
      }
    } catch (Exception e) {
      System.out.println("Não foi possivel excluir o empregado." + e);
    }
  }

  /**
   * Renderiza o menu de excluir departamento
   * @param input - um scanner
   */
  public void excluirDepartamento(Scanner input) {
    //Previne erro
    try {
      //Imprime a tabela de departamentos para o usuário saber qual o numero
      //do departamento que ele quer excluir
      DepartamentoDAO.selecionar(Menu.atrsDepartamento, "");

      System.out.print("Informe o numero do departamento que deseja excluir: ");
      int numero = Integer.parseInt(input.nextLine());
      System.out.println(Render.renderLine());
      System.out.print(
        "Por favor, confirme se deseja excluir o departamento:\n" + 
        "[0] - CONFIRMAR\n" + 
        "[1] - CANCELAR\n" + 
        "Resposta: "
      );

      int confirmacao = Integer.parseInt(input.nextLine());

      if (confirmacao == 0) {
        //Exclusão confirmada
        DepartamentoDAO.remover(numero);
      }
    } catch (Exception e) {
      System.out.println("Não foi possivel excluir o departamento." + e);
    }
  }

  /**
   * Renderiza o menu de excluir dependente
   * @param input - um scanner
   */
  public void excluirDependente(Scanner input){
    //Previne erro
    try {
      //Imprime a tabela de empregados para o usuário saber qual o cpf
      //do empregado relacionado com o dependente que ele quer excluir
      EmpregadoDAO.selecionar(Menu.atrsEmpregado, "");

      //Imprime a tabela de dependente para o usuário saber qual o nome
      //do dependente que ele quer excluir
      DependenteDAO.selecionar(Menu.atrsDependente, "");

      System.out.print("Informe o cpf do empregado do dependente que deseja excluir: ");
      String cpf = input.nextLine();
      System.out.print("Informe o nome do dependente que deseja excluir: ");
      String nome = input.nextLine();
      System.out.println(Render.renderLine());
      System.out.print(
        "Por favor, confirme se deseja excluir o dependente:\n" + 
        "[0] - CONFIRMAR\n" + 
        "[1] - CANCELAR\n" + 
        "Resposta: "
      );

      int confirmacao = Integer.parseInt(input.nextLine());

      if (confirmacao == 0) {
        //Exclusão confirmada
        DependenteDAO.remover(cpf, nome);
      }
    } catch (Exception e) {
      System.out.println("Não foi possivel excluir o dependente." + e);
    }
  }
}
