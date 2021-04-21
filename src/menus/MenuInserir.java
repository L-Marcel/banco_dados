package menus;

import java.util.Scanner;
import daos.*;
import entities.*;
import util.Render;

public class MenuInserir {
  /**
   * Renderiza o menu de escolha das entidades a serem inseridas
   * @param input - um scanner
   */
  public void menuInserir(Scanner input) {
    //Se esse valor for false volta para o menu inicial
    boolean continuar = true;

    //Mantem o menu inserir ativo
    while (continuar) {
      System.out.print(
        "Por favor, escolha de acordo com o número a tabela que deseja inserir dados:\n" + 
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

  /**
   * Renderiza o menu de inserir empregado
   * @param input - um scanner
   */
  public void inserirEmpregado(Scanner input) {
    //Previne erro
    try {      
      //Imprime a tabela de empregados para o usuário saber quais os cpfs
      //ele não pode usar para o empregado a ser inserido
      EmpregadoDAO.selecionar(Menu.atrsEmpregado, "");

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

      //Imprime a tabela de departamentos para o usuário saber qual o numero
      //do derpartamento que ele quer usar
      DepartamentoDAO.selecionar(Menu.atrsDepartamento, "");

      System.out.print("Informe o numero do departamento: ");
      int numDep = Integer.parseInt(input.nextLine());

      Empregado empregado = new Empregado(cpfEmpre, pnome, unome, dataNasc, endereco, salario, sexo, numDep);

      System.out.print("O empregado possui supervisor?\n[0] - Não\n[1] - Sim\nResposta: ");
      int possuiSupervisor = Integer.parseInt(input.nextLine());

      if (possuiSupervisor == 1) {
        //Imprime a tabela de empregados para o usuário saber qual o cpf
        //do supervisor que ele quer usar
        EmpregadoDAO.selecionar(Menu.atrsEmpregado, "");

        System.out.print("Informe o cpf do supervisor: ");
        String cpfSupervirsor = input.nextLine();

        empregado.setCpfSupervisor(cpfSupervirsor);
      }

      System.out.print(
        "Por favor, confirme se deseja inserir o empregado:\n" + 
        "[0] - CONFIRMAR\n" + 
        "[1] - CANCELAR\n" + 
        "Resposta: "
      );
      int confirmacao = Integer.parseInt(input.nextLine());

      if (confirmacao == 0) {
        //Inserção confirmada
        System.out.println(Render.renderLine() + "\n");
        EmpregadoDAO.adicionar(empregado);
      }
    } catch (Exception e) {
      System.out.println("Não foi possivel inserir o empregado.\n" + e.getMessage());
    }
  }

  /**
   * Renderiza o menu de inserir departamento
   * @param input - um scanner
   */
  public void inserirDepartamento(Scanner input) {
    //Previne erro
    try {
      //Imprime a tabela de departamentos para o usuário saber quais os numeros
      //ele não pode usar para o departamento a ser inserido
      DepartamentoDAO.selecionar(Menu.atrsDepartamento, "");
      System.out.print("Informe o numero do novo departamento: ");
      int numDep = Integer.parseInt(input.nextLine());

      //Imprime a tabela de empregados para o usuário saber qual o cpf
      //do empregado que ele quer usar
      EmpregadoDAO.selecionar(Menu.atrsEmpregado, "");

      System.out.print("Informe o cpf do gerente: ");
      String cpfGerente = input.nextLine();
      System.out.print("Informe o nome do departamento: ");
      String nome = input.nextLine();

      System.out.println("Informe a data de inicio do gerente");
      System.out.print("Dia: ");
      String dia = input.nextLine();
      System.out.print("Mês: ");
      String mes = input.nextLine();
      System.out.print("Ano: ");
      String ano = input.nextLine();

      String dataInicio = ano + "-" + mes + "-" + dia;

      System.out.print(
        "Por favor, confirme se deseja inserir o departamento:\n" + 
        "[0] - CONFIRMAR\n" + 
        "[1] - CANCELAR\n" + 
        "Resposta: "
      );
      
      int confirmacao = Integer.parseInt(input.nextLine());

      if (confirmacao == 0) {
        //Inserção confirmada
        System.out.println(Render.renderLine() + "\n");
        Departamento dep = new Departamento(numDep, nome, cpfGerente, dataInicio);
        DepartamentoDAO.adicionar(dep);
      }
    } catch (Exception e) {
      System.out.println("Não foi possivel inserir o departamento.\n" + e.getMessage());
    }
  }

  /**
   * Renderiza o menu de inserir dependente
   * @param input - um scanner
   */
  public void inserirDependente(Scanner input) {
    //Previne erro
    try {
      //Imprime a tabela de dependentes para o usuário saber quais os nomes
      //ele não pode usar para o dependente com o mesmo cpf a ser inserido
      DependenteDAO.selecionar(Menu.atrsDependente, "");

      System.out.print("Informe o nome do novo dependente: ");
      String nome = input.nextLine();

      //Imprime a tabela de empregados para o usuário saber quais os cpfs
      //ele não pode usar para o dependente com o mesmo nome a ser inserido
      EmpregadoDAO.selecionar(Menu.atrsEmpregado, "");
      System.out.print("Informe o cpf do empregado: ");
      String cpfEmpregado = input.nextLine();

      System.out.print("Informe o sexo do empregado: ");
      String sexo = input.nextLine();
      System.out.print("Informe o parentesco com o empregado: ");
      String parentesco = input.nextLine();

      System.out.println("Informe a data de nascimento do dependente.");
      System.out.print("Dia: ");
      String dia = input.nextLine();
      System.out.print("Mês: ");
      String mes = input.nextLine();
      System.out.print("Ano: ");
      String ano = input.nextLine();

      String dataNasc = ano + "-" + mes + "-" + dia;

      System.out.print(
          "Por favor, confirme se deseja inserir o dependente:\n" + 
          "[0] - CONFIRMAR\n" + 
          "[1] - CANCELAR\n" + 
          "Resposta: "
      );
        
      int confirmacao = Integer.parseInt(input.nextLine());

      if (confirmacao == 0) {
        //Inserção confirmada
        System.out.println(Render.renderLine() + "\n");
        Dependente dependente = new Dependente(nome, cpfEmpregado, dataNasc, sexo, parentesco);
        DependenteDAO.adicionar(dependente);
      }
    } catch (Exception e) {
      System.out.println("Não foi possivel inserir o depentende.\n" + e.getMessage());
    }
  }
}