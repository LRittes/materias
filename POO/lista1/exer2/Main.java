package lista1.exer2;

import java.util.ArrayList;
import java.util.List;

import lista1.exer1.Loja;
import lista1.exer1.Veiculo;

public class Main {
    public void main() {
        Veiculo veiculo1 = new Veiculo();
        Veiculo veiculo2 = new Veiculo("audi", 210000f, "ABC1234", "azul");
        List<Veiculo> lista1 = new ArrayList();
        lista1.add(veiculo1);
        lista1.add(veiculo2);
        Loja loja1 = new Loja();
        Loja loja2 = new Loja("loja1", lista1, 0.15f);

        System.out.println(veiculo1.getMarca());
        veiculo1.setMarca("Polo");
        System.out.println(veiculo1.getMarca());
        System.out.println(veiculo2.getPlaca());
        veiculo2.setPlaca("DEF5678");
        System.out.println(veiculo2.getPlaca());

    }
}
