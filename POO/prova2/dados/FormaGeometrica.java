package dados;

public abstract class FormaGeometrica {
    double x;
    double y;

    abstract double calcularPerimetro();

    abstract double calcularArea();

    abstract public String toString();

    abstract public String type();

    abstract public String props();

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}