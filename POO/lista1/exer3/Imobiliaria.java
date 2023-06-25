package lista1.exer3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Imobiliaria {
    private String nome;
    private List<Imovel> imoveis;

    public Imobiliaria(String nome) {
        this.nome = nome;
    }

    public void addImovel(Imovel imovel) {
        imoveis.add(imovel);
    }

    public List<Imovel> filtrarPorArea(float area) {
        List<Imovel> imoveisMock = new ArrayList<Imovel>();
        for (Imovel imovel : this.imoveis) {
            if (imovel.areaTotal() >= area)
                imoveisMock.add(imovel);
        }
        Collections.sort(imoveisMock);
        return imoveisMock;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Imovel> getImoveis() {
        return imoveis;
    }

    public void setImoveis(List<Imovel> imoveis) {
        this.imoveis = imoveis;
    }

    @Override
    public String toString() {
        return "Imobiliaria [imoveis=" + imoveis + ", nome=" + nome + "]";
    }

}
