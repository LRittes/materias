package lista1.exer1;

import java.util.List;

public class Loja {
    private String nome;
    private List<Veiculo> listaDeVeiculos;
    private float tax;

    public Loja(String nome, List<Veiculo> listaDeVeiculos, float tax) {
        this.nome = nome;
        this.listaDeVeiculos = listaDeVeiculos;
        this.tax = tax;
    }

    public Loja() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Veiculo> getListaDeVeiculos() {
        return listaDeVeiculos;
    }

    public void setListaDeVeiculos(List<Veiculo> listaDeVeiculos) {
        this.listaDeVeiculos = listaDeVeiculos;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "Loja [listaDeVeiculos=" + listaDeVeiculos + ", nome=" + nome + ", tax=" + tax + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Loja other = (Loja) obj;
        if (listaDeVeiculos == null) {
            if (other.listaDeVeiculos != null)
                return false;
        } else if (!listaDeVeiculos.equals(other.listaDeVeiculos))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (Float.floatToIntBits(tax) != Float.floatToIntBits(other.tax))
            return false;
        return true;
    }

}
