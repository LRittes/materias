package src.dados;

import java.util.ArrayList;
import java.util.List;

import src.exception.EspacoIndisponivelException;

public class Viveiro {
    
    protected String nome;
    protected float comprimento;
    protected float largura;
    List<Animal> animais = new ArrayList<Animal>();

    public float calculaEspaco(){
        return comprimento*largura;
    }

    private float espacoOcupado(){
        
        float espacoOcupado = 0;

        for(int i = 0 ; i < animais.size(); i++){
            espacoOcupado += animais.get(i).calculaEspacoOcupado();
        }

        return espacoOcupado;
    }

    public float espacoDisponivel(){

        float espacoTotal = calculaEspaco();

        return (espacoTotal - espacoOcupado());
    }

    public void adicionarAnimal(Animal animal) throws EspacoIndisponivelException{

        if (espacoDisponivel() > animal.calculaEspacoOcupado() * 0.7) {
            animais.add(animal);
        }else{
            throw new EspacoIndisponivelException();
        }

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getComprimento() {
        return comprimento;
    }

    public void setComprimento(float comprimento) {
        this.comprimento = comprimento;
    }

    public float getLargura() {
        return largura;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    public String toString() {
        return "Nome= "+ nome + "\nComprimento= " + comprimento + "\nLargura= " + largura +  "\nAnimais=" + animais;
    }
    
}