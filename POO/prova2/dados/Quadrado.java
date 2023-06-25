package dados;

public class Quadrado extends Quadrilatero {

    public Quadrado(double x, double y, double ladoA) {
        super(x, y, ladoA, ladoA, ladoA, ladoA);
    }

    @Override
    double calcularArea() {
        return ladoA * ladoA;
    }

    @Override
    public String toString() {
        return this.type() + ": Lado = " + ladoA + " Posição x = " + this.x + " Posição y = " + this.y + " Perimetro = "
                + calcularPerimetro()
                + " Área = " + calcularArea();
    }

    @Override
    public String type() {
        return "Quadrado";
    }

    @Override
    public String props() {
        return String.valueOf(this.ladoA);
    }

}
