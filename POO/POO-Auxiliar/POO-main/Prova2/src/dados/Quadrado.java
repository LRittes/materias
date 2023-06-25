package dados;

public class Quadrado extends Quadrilatero{

    public Quadrado(double x, double y, double tamLado){
        super(x,y,tamLado);
    }

    @Override
    public double CalculaArea() {
        return this.tam4lados*this.tam4lados;
    }

    @Override
    public String toString() {
        return "Quadrado{" +
                "x=" + x +
                ", y=" + y +
                ", tam4lados=" + tam4lados +
                '}';
    }
}
