package menus;

import java.sql.Connection;
import java.util.Scanner;

import util.Render;


public class Menu {


    MenuInserir mInserir = new MenuInserir();

    public void menu(Scanner input, Connection con) {
        boolean continuar = true;
        System.out.println("Olá! Seja bem vindo!");
        while (continuar) {
            System.out.println("Por gentileza, informe o número da opção desejada:\n[0] - Sair\n[1] - Visualizar uma tabela\n[2] - Inserir novos dados\n[3] - Excluir dados existentes");
            int opcaoEscolhida = Integer.parseInt(input.nextLine());
            System.out.println(Render.renderLine());
         

            if (opcaoEscolhida == 0) {
              
                continuar = sair(input);
                System.out.println(Render.renderLine());

            }if(opcaoEscolhida == 2){
                mInserir.menuInserir(input, con);
                
            } if(opcaoEscolhida == 3){

            }else{
                System.out.println("Descupe, não conseguimos entender o que você deseja, tente novamente!");
                System.out.println(Render.renderLine());
            }
        }
    }
    public boolean sair(Scanner input){

        System.out.println("Tem certeza que deseja sair? Digite o número com a opção desejada:\n[0] - Desejo sair\n[1] - Desejo voltar");
        int opcaoEscolhida = Integer.parseInt(input.nextLine());

        if (opcaoEscolhida == 0){
            System.out.println("Esperamos você de volta em breve!");
            return false;
        }else{
           return true;
        } 
    }
    
    
}
     