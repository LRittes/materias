package lista1.exer3;

public class Imovel implements Comparable<Imovel> {
    private float largura;
    private float comprimento;
    private float preco;

    public Imovel(float largura, float comprimento, float preco) {
        this.largura = largura;
        this.comprimento = comprimento;
        this.preco = preco;
    }

    public float areaTotal() {
        return this.comprimento * this.largura;
    }

    public float getLargura() {
        return largura;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

    public float getComprimento() {
        return comprimento;
    }

    public void setComprimento(float comprimento) {
        this.comprimento = comprimento;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Imovel [comprimento=" + comprimento + ", largura=" + largura + ", preco=" + preco + "]";
    }

    @Override
    public int compareTo(Imovel im) {
        return Math.round(im.areaTotal() - this.areaTotal());
    }

}
