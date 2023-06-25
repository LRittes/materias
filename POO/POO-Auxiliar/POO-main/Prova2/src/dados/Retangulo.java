package dados;

public class Retangulo extends Quadrilatero{

    private double tamBase;
    private double tamAltura;

    public Retangulo(double x, double y, double tamBase, double tamAltura) {
        super(x, y);
        this.tamBase = tamBase;
        this.tamAltura = tamAltura;
    }

    @Override
    public double CalculaPerimetro() {
        return 2 * tamBase + 2 * tamAltura;
    }

    @Override
    public double CalculaArea() {
        return tamBase*tamAltura;
    }

    @Override
    public String toString() {
        return "Retangulo{" +
                "tamBase=" + tamBase +
                ", tamAltura=" + tamAltura +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
