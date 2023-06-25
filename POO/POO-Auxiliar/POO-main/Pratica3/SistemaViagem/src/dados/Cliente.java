package src.dados;

public class Cliente {

    private long cpf;
    private String nome;
    private String endereco;
    private long telefone;
    private int quantReservas = 0;
    private Reserva [] reservas = new Reserva[100];

    public long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public Reserva [] getReservas() {
        return reservas;
    } 

    public void setReservas(Reserva[] reservas) {
        this.reservas = reservas;
    }

    public void reservarIda(Reserva reserva){
        if(quantReservas == reservas.length){
            System.out.println("Reservas cheias!!!");
        }else{
            reservas[quantReservas] = reserva;
            quantReservas++;
        }
    }

    public void reservarVolta(Reserva ida, Reserva volta){
        
        for(int i = 0; i < quantReservas ; i++){
            if(reservas [i] == ida ){
                reservas[i].setVolta(volta);
                reservas[quantReservas] = volta;
                quantReservas++;
            }
        }
    
    }

    public String toString(){
        return "CPF: "+cpf+"\nNome: "+nome+"\nEndereco: "+endereco+"\nTelefone: "+telefone;
    }
}
