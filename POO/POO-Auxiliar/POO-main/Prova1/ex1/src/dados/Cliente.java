package dados;

public class Cliente {

    private String nome;
    private double mensalidade;
    private double beneficio;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getMensalidade() {
        return (mensalidade - beneficio);
    }
    public void setMensalidade(double mensalidade) {
        this.mensalidade = mensalidade;
    }

    public double getBeneficio() {
        return beneficio;
    }
    public void setBeneficio(double beneficio) {
        this.beneficio = beneficio;
    }
    
}