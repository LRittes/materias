public class Veterinario {
    
    private String nome;
    private float salario;
    private Endereco endereco;
    private Animal [] animais = new Animal[50];
    private int quantidadeAnimais;
    
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setSalario(float salario){
        this.salario = salario;
    }
    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }
    public void setAnimais(Animal pet,  int quantidadeAnimais){
        this.animais[quantidadeAnimais] = pet;
    }
    public void setQuantidadeAnimais(int quantidadeAnimais){
        this.quantidadeAnimais += quantidadeAnimais;
    }
    
    public String getNome(){
        return this.nome;
    }
    public float getSalario(){
        return this.salario;
    }
    public Endereco getEndereco(){
        return this.endereco;
    }
    public Animal[] getAnimais(){
        return this.animais;
    }
    public int getQuantidadeAnimais(){
        return this.quantidadeAnimais;
    }

}
