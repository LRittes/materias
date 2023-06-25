package dados;

public class TrianguloEquilatero extends FormaGeometrica {

    double ladoA;

    public TrianguloEquilatero(double x, double y, double ladoA) {
        this.ladoA = ladoA;
        this.x = x;
        this.y = y;
    }

    @Override
    double calcularPerimetro() {
        return 3 * ladoA;
    }

    @Override
    double calcularArea() {
        return Math.pow(ladoA, 2) * Math.sqrt(3) / 4;
    }

    @Override
    public String toString() {
        return this.type() + ": Comprimento = " + ladoA + " Posição x = " + this.x + " Posição y = " + this.y
                + " Perimetro = "
                + calcularPerimetro() + " Área = " + calcularArea();

    }

    @Override
    public String type() {
        return "TrianguloEquilatero";
    }

    @Override
    public String props() {
        return String.valueOf(this.ladoA);
    }

}
