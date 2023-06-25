import dados.Matriz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PrincipalMatriz{
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        int op=0;
        while(op != -1){
            Matriz<Integer> mat = new Matriz<>(5,5);
            for(int i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    System.out.print("mat["+i+"]["+j+"] = ");
                    mat.set(Integer.parseInt(scanner.nextLine()), i, j);
                }
            }
            List<Integer> menor = new ArrayList<>();
            menor.add(Collections.min(mat.getElementosQuadrante(Matriz.Quadrante.PRIMEIRO)));
            menor.add(Collections.min(mat.getElementosQuadrante(Matriz.Quadrante.SEGUNDO)));
            menor.add(Collections.min(mat.getElementosQuadrante(Matriz.Quadrante.TERCEIRO)));
            menor.add(Collections.min(mat.getElementosQuadrante(Matriz.Quadrante.QUARTO)));
            System.out.println("Matriz: \n" + mat);
            System.out.println("Menor: " + Collections.min(menor));
            System.out.print("Digite qualquer valor para continuar(-1 para para): ");
            op = Integer.parseInt(scanner.nextLine());
        }
    }

}