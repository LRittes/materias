public class Documento extends Arquivo {
    String texto;

    public Documento(String nome) {
        super(nome + ".txt");
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "\t" + this.getNome();
    }
}
