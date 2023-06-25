package dados;

public class Circulo extends FormaGeometrica {
    double raio;

    public Circulo(double x, double y, double raio) {
        this.raio = raio;
        this.x = x;
        this.y = y;
    }

    @Override
    double calcularPerimetro() {
        return 2 * Math.PI * raio;
    }

    @Override
    double calcularArea() {
        return Math.pow(raio, 2) * Math.PI;
    }

    @Override
    public String toString() {
        return this.type() + ": Raio = " + raio + " Posição x = " + this.x + " Posição y = " + this.y + " Perimetro = "
                + calcularPerimetro()
                + " Área = " + calcularArea();
    }

    @Override
    public String type() {
        return "Circulo";
    }

    @Override
    public String props() {
        return String.valueOf(this.raio);
    }

}
