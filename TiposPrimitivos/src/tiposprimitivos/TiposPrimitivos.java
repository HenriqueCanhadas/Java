
package tiposprimitivos;

import java.util.Scanner;

public class TiposPrimitivos {

    public static void main(String[] args) {
        
        System.out.print("Digite seu nome:");
        Scanner teclado = new Scanner(System.in);
        String nome = teclado.nextLine();
        
        System.out.print("Digite sua nota:");
        float nota = teclado.nextFloat();
        
        System.out.println("Sua nota e:"+nota);
        System.out.printf("Sua nota %s seria de:%.2f \n", nome,nota);
        
        int idade = 20;
        String valor = Integer.toString(idade);
        System.out.println(valor);
        
        String quantidade = "40";
        
        int numero = Integer.parseInt(quantidade);
        
        System.out.println(numero);
        
    }
    
}
