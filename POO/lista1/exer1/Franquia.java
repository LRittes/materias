package lista1.exer1;

import java.util.List;

public class Franquia {
    private String marca;
    private List<String> paises;
    private List<Loja> lojas;

    public Franquia() {
    }

    public Franquia(String marca, List<String> paises, List<Loja> lojas) {
        this.marca = marca;
        this.paises = paises;
        this.lojas = lojas;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public List<String> getPaises() {
        return paises;
    }

    public void setPaises(List<String> paises) {
        this.paises = paises;
    }

    public List<Loja> getLojas() {
        return lojas;
    }

    public void setLojas(List<Loja> lojas) {
        this.lojas = lojas;
    }

    @Override
    public String toString() {
        return "Franquia [lojas=" + lojas + ", marca=" + marca + ", paises=" + paises + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Franquia other = (Franquia) obj;
        if (lojas == null) {
            if (other.lojas != null)
                return false;
        } else if (!lojas.equals(other.lojas))
            return false;
        if (marca == null) {
            if (other.marca != null)
                return false;
        } else if (!marca.equals(other.marca))
            return false;
        if (paises == null) {
            if (other.paises != null)
                return false;
        } else if (!paises.equals(other.paises))
            return false;
        return true;
    }

}
