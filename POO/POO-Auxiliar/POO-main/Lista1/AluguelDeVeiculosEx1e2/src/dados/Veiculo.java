package dados;
import java.util.Objects;

public class Veiculo {

    private String modelo;
    private String marca;
    private int ano;
    private Data dataVencimento;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Data getData() {
        return dataVencimento;
    }

    public void setData(Data data) {
        this.dataVencimento = data;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return ano == veiculo.ano &&
                Objects.equals(modelo, veiculo.modelo) &&
                Objects.equals(marca, veiculo.marca) && 
                Objects.equals(dataVencimento, veiculo.dataVencimento);
    }

    public String toString() {
        return "Modelo=" + modelo + "\nMarca=" + marca + "\nAno=" + ano + "\n---Data Vencimento---\n" + dataVencimento.toString();
    }
}
