package menus;

import java.sql.Date;
import java.util.Scanner;
import daos.*;
import entities.*;
import util.*;

public class MenuAtualizar {
  /**
   * Renderiza o menu de escolha das entidades a serem atualizadas
   * @param input - um scanner
   */
  public void menuAtualizar(Scanner input) {
    //Se esse valor for false volta para o menu inicial
    boolean continuar = true;

    //Mantem o menu atualizar ativo
    while (continuar) {
      System.out.print(
        "Por favor, escolha de acordo com o número a tabela que deseja atualizar:\n" +
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
        atualizarEmpregado(input);
        System.out.println(Render.renderLine());
      } else if (opcaoEscolhida == 2) {
        atualizarDepartamento(input);
        System.out.println(Render.renderLine());
      } else if (opcaoEscolhida == 3) {
        atualizarDependente(input);
        System.out.println(Render.renderLine());
      } else {
        System.out.println("Descupe, não conseguimos entender o que você deseja, tente novamente!");
        System.out.println(Render.renderLine());
      }
    }
  }

  /**
   * Renderiza o menu de atualizar empregado
   * @param input - um scanner
   */
  public void atualizarEmpregado(Scanner input) {
    //Previne erro
    try {
      //Imprime a tabela de empregados para o usuário saber qual o cpf
      //do empregado que ele quer atualizar
      EmpregadoDAO.selecionar(Menu.atrsEmpregado, "");

      System.out.print("Informe o cpf do empregado: ");
      String cpf = input.nextLine();
      Empregado empregado = EmpregadoDAO.selecionar(cpf);

      //Se esse valor for false volta para o menu de atualizar
      boolean continuar = true;

      while(continuar){
        System.out.println(Render.renderLine());
        System.out.print(
          "Informe o campo que deseja alterar.\n" + 
          "[0] - CONFIRMAR ALTERAÇÔES\n" +
          "[1] - CANCELAR ALTERAÇÔES\n" +
          "[2] - CPF (" + empregado.getCpf() + ")\n" + 
          "[3] - Primeiro nome (" + empregado.getPnome() + ")\n" + 
          "[4] - Último nome (" + empregado.getUnome() + ")\n" + 
          "[5] - Data de nascimento (" + empregado.getDataNasc() + ")\n" + 
          "[6] - Sálario (" + empregado.getSalario() + ")\n" + 
          "[7] - Endereço (" + empregado.getEndereco() + ")\n" + 
          "[8] - Sexo (" + empregado.getSexo() + ")\n" + 
          "[9] - Número do departamento (" + empregado.getNumeroDep() + ")\n" + 
          "[10] - CPF do supervisor (" + empregado.getCpfSupervisor() + ")\n" + 
          "Resposta: "
        );
        
        int opcaoEscolhida = Integer.parseInt(input.nextLine());
        System.out.println(Render.renderLine() + "\n");

        //Posiveis casos
        switch (opcaoEscolhida) {
          case 0:
            //Alteração confirmada
            EmpregadoDAO.atualizar(cpf, empregado);
            continuar = false;
            break;
          case 1:
            //Alteração recusada
            continuar = false;
            break;
          case 2:
            System.out.print("Informe o CPF do empregado: ");
            String cpfEmpre = input.nextLine();
            empregado.setCpf(cpfEmpre);
            System.out.println();
            break;
          case 3:
            System.out.print("Informe o primeiro nome do empregado: ");
            String pnome = input.nextLine();
            empregado.setPnome(pnome);
            System.out.println();
            break;
          case 4:
            System.out.print("Informe o último nome do empregado: ");
            String unome = input.nextLine();
            empregado.setUnome(unome);
            System.out.println();
            break;
          case 5:
            System.out.println("Informe a data de nascimento do empregado.");
            System.out.print("Dia: ");
            String dia = input.nextLine();
            System.out.print("Mês: ");
            String mes = input.nextLine();
            System.out.print("Ano: ");
            String ano = input.nextLine();

            String dataNasc = ano + "-" + mes + "-" + dia;
            empregado.setDataNasc(Date.valueOf(dataNasc));
            System.out.println();
            break;
          case 6:
            System.out.print("Informe o sálario do empregado, (Exemplo: 10000): ");
            Double salario = Double.parseDouble(input.nextLine());
            empregado.setSalario(salario);
            System.out.println();
            break;
          case 7:
            System.out.print("Informe o endereço do empregado: ");
            String endereco = input.nextLine();
            empregado.setEndereco(endereco);
            System.out.println();
            break;
          case 8:
            System.out.print("Informe o sexo do empregado (F, M ou O): ");
            String sexo = input.nextLine();
            empregado.setSexo(sexo);
            System.out.println();
            break;
          case 9:
            System.out.print("Informe o numero do departamento: ");
            int numDep = Integer.parseInt(input.nextLine());
            empregado.setNumeroDep(numDep);
            System.out.println();
            break;
          case 10:
            System.out.print("Informe o cpf do supervisor: ");
            String cpfSupervisor = input.nextLine();
            empregado.setCpfSupervisor(cpfSupervisor);
            System.out.println();
            break;
          default:
            //Alteraçõo recusada
            continuar = false;
            break;
        }
      }
    } catch (Exception e) {
      System.out.println("Não foi possivel atualizar o empregado\n" + e.getMessage());
    }
  }

  /**
   * Renderiza o menu de atualizar departamento
   * @param input - um scanner
   */
  public void atualizarDepartamento(Scanner input) {
    //Previne erro
    try {
      //Imprime a tabela de empregados para o usuário saber qual o cpf
      //do empregado que ele quer atualizar
      DepartamentoDAO.selecionar(Menu.atrsDepartamento, "");
      
      System.out.print("Informe o número do departamento: ");
      int numDep = Integer.parseInt(input.nextLine());
      Departamento departamento = DepartamentoDAO.selecionar(numDep);
      DepartamentoDAO.atualizar(numDep, departamento);

      //Se esse valor for false volta para o menu de atualizar
      boolean continuar = true;

      while(continuar){
        System.out.println(Render.renderLine());
        System.out.print(
            "Informe o campo que deseja alterar.\n" + 
            "[0] - CONFIRMAR ALTERAÇÔES\n" +
            "[1] - CANCELAR ALTERAÇÔES\n" +
            "[2] - Numero (" + departamento.getNumero() + ")\n" + 
            "[3] - Nome (" + departamento.getNome() + ")\n" + 
            "[4] - CPF do gerente (" + departamento.getCpfGerente() + ")\n" + 
            "[5] - Data de inicio do gerente (" + departamento.getDataIniGerente() + ")\n" +
            "Resposta: "
        );
        
        int opcaoEscolhida = Integer.parseInt(input.nextLine());
        System.out.println(Render.renderLine() + "\n");

        //Posiveis casos
        switch (opcaoEscolhida) {
          case 0:
              //Alteração confirmada
              DepartamentoDAO.atualizar(numDep, departamento);
              continuar = false;
              break;
          case 1:
              //Alteração recusada
              continuar = false;
              break;
          case 2:
              System.out.print("Informe o número do departamento: ");
              int numeroDep = Integer.parseInt(input.nextLine());
              departamento.setNumero(numeroDep);
              System.out.println();
              break;
          case 3:
              System.out.print("Informe o nome do departamento: ");
              String nomeDep = input.nextLine();
              departamento.setNome(nomeDep);
              System.out.println();
              break;
          case 4:
              System.out.print("Informe o CPF do gerente: ");
              String cpfGerente = input.nextLine();
              departamento.setCpfGerente(cpfGerente);
              System.out.println();
              break;
          case 5:
              System.out.println("Informe a data de inicio do gerente.");
              System.out.print("Dia: ");
              String dia = input.nextLine();
              System.out.print("Mês: ");
              String mes = input.nextLine();
              System.out.print("Ano: ");
              String ano = input.nextLine();

              String dataInicioGerente = ano + "-" + mes + "-" + dia;

              departamento.setDataIniGenrente(Date.valueOf(dataInicioGerente));
              System.out.println();
              break;
          default:
            //Alteração recusada
            continuar = false;
            break;
        }
      }
    } catch (Exception e) {
      System.out.println("Não foi possivel atualizar o departamento\n" + e.getMessage());
    }
  }

  /**
   * Renderiza o menu de atualizar dependente
   * @param input - um scanner
   */
  public void atualizarDependente(Scanner input) {
    //Previne erro
    try {
        //Imprime a tabela de empregados para o usuário saber qual o cpf
        //do empregado relacionado com o dependente que ele quer atualizar
        EmpregadoDAO.selecionar(Menu.atrsEmpregado, "");

        //Imprime a tabela de dependente para o usuário saber qual o nome
        //do dependente que ele quer atualizar
        DependenteDAO.selecionar(Menu.atrsDependente, "");

        System.out.print("Informe o cpf do empregado relacionado ao dependente: ");
        String cpfEmpre = input.nextLine();
        System.out.print("Informe o nome do dependente: ");
        String nomeDep = input.nextLine();
        System.out.print(nomeDep);
        Dependente dependente = DependenteDAO.selecionar(cpfEmpre, nomeDep);
        
        //Se esse valor for false volta para o menu de atualizar
        boolean continuar = true;

        while(continuar){
          System.out.println(Render.renderLine());
          System.out.print(
              "Informe o campo que deseja alterar.\n" + 
              "[0] - CONFIRMAR ALTERAÇÔES\n" +
              "[1] - CANCELAR ALTERAÇÔES\n" +
              "[2] - Nome (" + dependente.getNome() + ")\n" + 
              "[3] - CPF do empregado (" + dependente.getCpfEmpregado() + ")\n" + 
              "[4] - Sexo (" + dependente.getSexo() + ")\n" + 
              "[5] - Data de nascimento (" + dependente.getDataNasc() + ")\n" +
              "Resposta: "
          );

          int opcaoEscolhida = Integer.parseInt(input.nextLine());
          System.out.println(Render.renderLine() + "\n");

          //Posiveis casos
          switch (opcaoEscolhida) {
            case 0:
              //Alteração confirmada
              DependenteDAO.atualizar(cpfEmpre, nomeDep, dependente);
              continuar = false;
                break;
            case 1:
              //Alteração recusada
              continuar = false;
              break;
            case 2:
              System.out.print("Informe o nome do dependente: ");
              String nome = input.nextLine();
              dependente.setNome(nome);
              System.out.println();
              break;
            case 3:
              System.out.print("Informe o cpf do empregado relacionado: ");
              long cpfEmpregado = Converter.cpfStringToLong(input.nextLine());
              dependente.setCpfEmpregado(cpfEmpregado);
              System.out.println();
              break;
            case 4:
              System.out.print("Informe o sexo do dependente: ");
              String sexo = input.nextLine();
              dependente.setSexo(sexo);
              System.out.println();
              break;
            case 5:
              System.out.print("Informe o parentesco com o empregado: ");
              String parentesco = input.nextLine();
              dependente.setParentesco(parentesco);
              System.out.println();
              break;
            case 6:
              System.out.println("Informe a data de nascimento do dependente.");
              System.out.print("Dia:");
              String dia = input.nextLine();
              System.out.print("Mês:");
              String mes = input.nextLine();
              System.out.print("Ano:");
              String ano = input.nextLine();

              String dataNasc = ano + "-" + mes + "-" + dia;
              dependente.setDataNasc(Date.valueOf(dataNasc));
              System.out.println();
              break;
            default:
              //Alteração recusada
              continuar = false;
              break;
          }
      }
    } catch (Exception e) {
      System.out.println("Não foi possivel atualizar o dependente.\n" + e.getMessage());
    }
  }
}
