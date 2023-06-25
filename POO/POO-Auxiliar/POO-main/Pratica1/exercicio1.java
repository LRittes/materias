import java.util.Scanner;

public class exercicio1 {
    public static void main(String[] args) {
        
        try (Scanner leitor = new Scanner(System.in)) {
            
            int [] valores = new int [5];
            float media = 0;
            int i = 0;

            for(i = 0; i < 5 ; i++){
                valores[i] = leitor.nextInt();
                media += valores[i];
            }

            System.out.println("Media = " + media / 5);
        }

    }
    
}
