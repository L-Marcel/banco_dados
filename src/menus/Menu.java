package menus;

import java.util.Scanner;

import util.Render;

public class Menu {
  //As colunas importantes de cada tabela para quando forem imprimidas
  static String[] atrsDepartamento = { "numero", "nome" };
  static String[] atrsEmpregado = { "cpf", "pnome", "unome", "numero_dep" };
  static String[] atrsDependente = { "nome", "cpf_empregado", "parentesco" };

  //Todos os menus
  MenuInserir mInserir = new MenuInserir();
  MenuVisualizar mVisualizar = new MenuVisualizar();
  MenuAtualizar mAtualizar = new MenuAtualizar();
  MenuExcluir mExcluir = new MenuExcluir();

  /**
   * Renderiza o menu inicial
   * @param input - um scanner
   */
  public void render(Scanner input) {
    //Se esse valor for false a aplicação é parada
    boolean continuar = true;

    System.out.println("Olá! Seja bem vindo!");

    //Mantem o menu inicial ativo
    while (continuar) {
      System.out.print(
        "Por gentileza, informe o número da opção desejada:\n" + 
        "[0] - Sair\n" + 
        "[1] - Visualizar uma tabela\n" + 
        "[2] - Inserir novos dados\n" + 
        "[3] - Excluir dados existentes\n" + 
        "[4] - Atualizar dados existentes\n" + 
        "Resposta: "
      );
      int opcaoEscolhida = Integer.parseInt(input.nextLine());
      System.out.println(Render.renderLine());

      //Possiveis casos
      if (opcaoEscolhida == 0) {
        continuar = sair(input); //Retorna true ou false
        System.out.println(Render.renderLine());
      } else if (opcaoEscolhida == 1) {
        mVisualizar.menuVisualizar(input);
      } else if (opcaoEscolhida == 2) {
        mInserir.menuInserir(input);
      } else if (opcaoEscolhida == 3) {
        mExcluir.menuExcluir(input);
      } else if (opcaoEscolhida == 4) {
        mAtualizar.menuAtualizar(input);
      } else {
        System.out.println("Descupe, não conseguimos entender o que você deseja, tente novamente!");
        System.out.println(Render.renderLine());
      }
    }
  }

  /**
   * Renderiza o menu que confirmará se o usuário quer sair
   * @param input - um scanner
   */
  public boolean sair(Scanner input) {
    System.out.print(
      "Tem certeza que deseja sair? Digite o número com a opção desejada:\n" + 
      "[0] - Desejo sair\n" + 
      "[1] - Desejo voltar\n" + 
      "Resposta: "
    );
    int opcaoEscolhida = Integer.parseInt(input.nextLine());

    //Posiveis casos
    if (opcaoEscolhida == 0) {
      System.out.println("Esperamos você de volta em breve!");
      return false;
    } else {
      return true;
    }
  }
}
