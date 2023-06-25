package dados;

public abstract class Animal{
    String nome;

    public String emitirSom(){
        return "";
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}