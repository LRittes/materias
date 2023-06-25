package dados;

public class Imoveis {

    private int largura;
    private int comprimento;
    private int valor;

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getComprimento() {
        return comprimento;
    }

    public void setComprimento(int comprimento) {
        this.comprimento = comprimento;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public float areaTotal(){
        return comprimento * largura;
    }

    public String toString() {
        return "Comprimento =" + comprimento + ", largura=" + largura + ", valor=" + valor + "]";
    }    

}