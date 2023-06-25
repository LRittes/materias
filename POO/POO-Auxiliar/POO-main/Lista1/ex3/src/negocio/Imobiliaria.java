package negocio;

import dados.Imoveis;

import java.util.Arrays;

public class Imobiliaria {

    private String nome;
    private Imoveis[] imoveisVenda = new Imoveis[20];
    private int contImoveis = 0;

    public String toString() {
        return "\n---Imobiliaria "+nome+"---\n"+"Imoveis Venda" + Arrays.toString(imoveisVenda);
    }

    public void setImovel(Imoveis imovel){
        this.imoveisVenda[contImoveis] = imovel;
        contImoveis++;
    }

    public Imoveis [] filtrarPorArea(float x){
        
        Imoveis[] imoveisMaiorX = new Imoveis[this.contaArea(x)];
        int contMaiores = 0;

        for(int i = 0; i < contMaiores ; i++){
            if(x <= this.imoveisVenda[i].areaTotal()){
                imoveisMaiorX[contMaiores] = this.imoveisVenda[i];
                contMaiores++;
            }
        }
        return this.ordena(imoveisMaiorX);

    }
    private int contaArea(Float x){
        int cont = 0;

        for(int i = 0 ; i < contImoveis ; i++ ){
            if(this.imoveisVenda[i].areaTotal() >= x){
                cont++;
            }
        }
        return cont;
    }
    private Imoveis[] ordena(Imoveis [] ordem){
        for(int i = 0; i< ordem.length ; i++){
            for(int j = 0; j<ordem.length;j++){
                if(ordem[i].getValor() > ordem[j].getValor()){
                    Imoveis aux = ordem[j];
                    ordem[j] = ordem[i];
                    ordem[i] = aux;
                }
            }
        }
        return ordem;
    }
    

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Imoveis[] getImoveisVenda() {
        return imoveisVenda;
    }
    public void setImoveisVenda(Imoveis[] imoveisVenda) {
        this.imoveisVenda = imoveisVenda;
    }
    public int getContImoveis() {
        return contImoveis;
    }
    public void setContImoveis(int contImoveis) {
        this.contImoveis = contImoveis;
    }



}