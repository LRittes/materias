package dados;

public class Juiz {
    private String nome;
    private int limite;
    private Pilha<Processo> processos;

    public Juiz(int limite) {
        this.limite = limite;
        this.processos = new Pilha<>(limite);
    }

    public void cadastrarProcesso(Processo processo) {
        this.processos.insere(processo);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pilha<Processo> getProcessos() {
        return processos;
    }

    public int getLimite() {
        return limite;
    }

}
