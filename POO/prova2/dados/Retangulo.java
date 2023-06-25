package dados;

public class Retangulo extends Quadrilatero {

    public Retangulo(double x, double y, double ladoA, double ladoB) {
        super(x, y, ladoA, ladoB, ladoA, ladoB);
    }

    @Override
    double calcularArea() {
        return ladoA * ladoB;
    }

    @Override
    public String toString() {
        return this.type() + ": Base: " + ladoA + " Altura: " + ladoB + " Posição x = " + this.x + " Posição y = "
                + this.y
                + " Perimetro = " + calcularPerimetro()
                + " Área = " + calcularArea();
    }

    @Override
    public String type() {
        return "Retangulo";
    }

    @Override
    public String props() {
        return String.valueOf(this.ladoA) + ";" + String.valueOf(this.ladoB);
    }

}
