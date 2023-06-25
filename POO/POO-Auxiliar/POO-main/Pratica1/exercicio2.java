import java.util.Scanner;

public class exercicio2 {
    public static void main(String[] args) {
        
        Scanner leitor = new Scanner(System.in);
        String [] nomes = new String [3];
        int [] idade = new int [3];
        int i;

        for(i = 0 ; i < nomes.length; i++){
            System.out.println("Digite o nome da pessoa ["+ (i+1) +"]:");
            nomes[i] = leitor.nextLine();
            System.out.println("Digite a idade da pessoa[" + (i+1) + "]:");
            idade[i] = Integer.valueOf(leitor.nextLine());
        }
            
        for(i = nomes.length-1 ; i >= 0 ; i--){
            System.out.println("Nome e idade digitados para a pessoa " + (i +1)+ ":");
            System.out.println(nomes[i]+ ", " + idade[i] + " anos" );
        }

        leitor.close();

    }
}
