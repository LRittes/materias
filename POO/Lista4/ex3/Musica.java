public class Musica extends Arquivo {
    int duracao;

    public Musica(String nome) {
        super(nome + ".mp3");
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getDuracao() {
        return duracao;
    }

    @Override
    public String toString() {
        return "\t" + this.getNome() + "\n\tDuracao: " + getDuracao();
    }
}
