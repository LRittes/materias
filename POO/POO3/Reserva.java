public class Reserva {
    private int numReserva;
    private String dataVoo;
    private String horaVoo;
    private float preco;
    private String classVoo;
    private boolean idaEVolta;
    private int poltrona;
    private Cidade origem;
    private Cidade destino;

    public Cidade getOrigem() {
        return origem;
    }

    public void setOrigem(Cidade origem) {
        this.origem = origem;
    }

    public Cidade getDestino() {
        return destino;
    }

    public void setDestino(Cidade destino) {
        this.destino = destino;
    }

    public Reserva(int numReserva, String dataVoo, String horaVoo, float preco, String classVoo, boolean idaEVolta,
            int poltrona) {
        this.numReserva = numReserva;
        this.dataVoo = dataVoo;
        this.horaVoo = horaVoo;
        this.preco = preco;
        this.classVoo = classVoo;
        this.idaEVolta = idaEVolta;
        this.poltrona = poltrona;
    }

    public int getNumReserva() {
        return numReserva;
    }

    public void setNumReserva(int numReserva) {
        this.numReserva = numReserva;
    }

    public String getDataVoo() {
        return dataVoo;
    }

    public void setDataVoo(String dataVoo) {
        this.dataVoo = dataVoo;
    }

    public String getHoraVoo() {
        return horaVoo;
    }

    public void setHoraVoo(String horaVoo) {
        this.horaVoo = horaVoo;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getClassVoo() {
        return classVoo;
    }

    public void setClassVoo(String classVoo) {
        this.classVoo = classVoo;
    }

    public boolean isIdaEVolta() {
        return idaEVolta;
    }

    public void setIdaEVolta(boolean idaEVolta) {
        this.idaEVolta = idaEVolta;
    }

    public int getPoltrona() {
        return poltrona;
    }

    public void setPoltrona(int poltrona) {
        this.poltrona = poltrona;
    }

    @Override
    public String toString() {
        return "Reserva [classVoo=" + classVoo + ", dataVoo=" + dataVoo + ", horaVoo=" + horaVoo + ", idaEVolta="
                + idaEVolta + ", numReserva=" + numReserva + ", poltrona=" + poltrona + ", preco=" + preco + "]";
    }

}
