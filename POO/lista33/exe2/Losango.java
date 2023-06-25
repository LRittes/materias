package exe2;

public class Losango extends FormaGeometrica {

    public Losango(int medida1, int medida2) {
        this.medida1 = medida1;
        this.medida2 = medida2;
    }

    @Override
    public double calcularArea() {
        return (medida1 * medida2) / 2;
    }

    @Override
    public double calcularPerimetro() {
        double x = Math.pow((medida2 * medida2) + (medida1 * medida1), 0.5);
        return 4 * x;
    }

    @Override
    public String toString() {
        return "Losango " + medida1 + " " + medida2;
    }

}
