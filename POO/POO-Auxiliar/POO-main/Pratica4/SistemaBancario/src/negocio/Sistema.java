package negocio;

import java.util.ArrayList;
import java.util.List;

import dados.ContaBancaria;
import dados.ContaCorrente;
import dados.ContaSalario;

public class Sistema {

    private List<ContaBancaria> contasBancarias = new ArrayList<ContaBancaria>(); 

    public void cadastrarConta(ContaBancaria conta){
        contasBancarias.add(conta);
    }

    public void realizarSaque(ContaBancaria conta, float valor){
        
        for(int i = 0 ; i < contasBancarias.size() ; i++){
            if(contasBancarias.get(i).equals(conta) == true){
                contasBancarias.get(i).sacar(valor);
                contasBancarias.get(i).gerarExtrato();
            }
        }

    }

    public boolean realizarDeposito(ContaCorrente conta, float valor){
        return conta.depositar(valor);
    }

    public boolean realizarDeposito(ContaSalario conta, float valor, String cnpj){
        return conta.depositar(valor, cnpj);
    }

    public List<ContaCorrente> getContasCorrentes(){

        List<ContaCorrente> contasCorrentes = new ArrayList<ContaCorrente>();

        for(int i = 0 ; i < contasBancarias.size() ; i++){
            if(contasBancarias.get(i) instanceof ContaCorrente){
                contasCorrentes.add((ContaCorrente)(contasBancarias.get(i)));
            }
        }

        return contasCorrentes;
    }

    public List<ContaSalario> getContaSalarios(){

        List<ContaSalario> contasSalarios = new ArrayList<ContaSalario>();

        for(int i = 0; i < contasBancarias.size() ; i++){
            if(contasBancarias.get(i) instanceof ContaSalario){
                contasSalarios.add((ContaSalario)(contasBancarias.get(i)));
            }
        }

        return contasSalarios;
    }

    public List<ContaBancaria> getContaBancarias(){
        return contasBancarias;
    }

    public String obterExtrato(ContaBancaria conta){
        return conta.gerarExtrato();
    }


}