
public class Aluno implements Comparable<Aluno> {
    private String nome;
    private int idad;
    private double[] notas = new double[5];

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idad;
    }

    public void setIdade(int idad) {
        this.idad = idad;
    }

    public double[] getnotasas() {
        return notas;
    }

    public void setNotas(double[] notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
                ", Media: " + calcularMedia();
    }

    @Override
    public int compareTo(Aluno b) {
        if (this.calcularMedia() > b.calcularMedia()) {
            return -1;
        }
        if (this.calcularMedia() < b.calcularMedia()) {
            return 1;
        }
        return 0;
    }

    public double calcularMedia() {
        double n = 0.0;

        for (int i = 0; i < 5; i++) {
            n += notas[i];
        }

        return n / 5.0;
    }
}
