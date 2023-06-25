package exe2;

public class Circulo extends FormaGeometrica {

    public Circulo(int raio) {
        this.medida1 = raio;
    }

    @Override
    public double calcularArea() {
        return 3.14 * medida1 * medida1;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * 3.14 * medida1;
    }

    @Override
    public String toString() {
        return "Circulo: raio " + medida1;
    }

}
