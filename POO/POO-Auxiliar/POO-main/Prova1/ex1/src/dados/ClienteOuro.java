package dados;

public class ClienteOuro extends Cliente{
    
    private int quantDependentes;

    public double getMensalidade() {
        return (super.getMensalidade()*0.8);
    }

    public double getBeneficio(){
        return (super.getBeneficio() *1.1);
    }

    public void setQuantDependentes(int quantDependentes) {
        this.quantDependentes = quantDependentes;
    }
    public int getQuantDependentes() {
        return quantDependentes;
    }

}