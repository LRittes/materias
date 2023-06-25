import java.util.Arrays;

public class Aluno {

    private String nome;
    private int idade;
    private double[] notas = new double[5];
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public double[] getNotas() {
        return notas;
    }
    public void setNotas(double[] notas) {
        this.notas = notas;
    }

    public double calcularMedia(){
        
        double soma =0;
        
        for(double nota : this.notas){
            soma  += nota;
        }

        return soma/5;
    }

    public String toString() {
        return "Idade=" + idade + ", nome=" + nome + ", notas=" + Arrays.toString(notas) + "]";
    }

    public int compareTo(Aluno o) {
        if(calcularMedia()<o.calcularMedia()){
            return -1;
        }else if(calcularMedia()>o.calcularMedia()){
            return 1;
        }else {
            return 0;
        }
    }
    
}