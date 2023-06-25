package dados;

public abstract class Quadrilatero extends FormaGeometrica {
    double ladoA;
    double ladoB;
    double ladoC;
    double ladoD;

    public Quadrilatero(double x, double y, double ladoA, double ladoB, double ladoC, double ladoD) {
        this.x = x;
        this.y = y;
        this.ladoA = ladoA;
        this.ladoB = ladoB;
        this.ladoC = ladoC;
        this.ladoD = ladoD;
    }

    @Override
    double calcularPerimetro() {
        return ladoA + ladoB + ladoC + ladoD;
    }

    @Override
    abstract public String toString();

    @Override
    abstract public String type();

    @Override
    abstract public String props();

}
