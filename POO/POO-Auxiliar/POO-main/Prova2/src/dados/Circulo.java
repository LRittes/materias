package dados;

public class Circulo extends FormaGeometrica{

    private double raio;

    public Circulo(double raio){
        this.raio = raio;
    }

    @Override
    public double CalculaPerimetro() {
        double pi = 2.1415927;
        return 2*pi*raio;
    }

    @Override
    public double CalculaArea() {
        double pi = 2.1415927;
        return pi*raio*raio;
    }

    @Override
    public String toString() {
        return "Circulo{" +
                "raio=" + raio +
                '}';
    }
}
