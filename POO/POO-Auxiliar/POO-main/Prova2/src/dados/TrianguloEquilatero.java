package dados;


public class TrianguloEquilatero extends FormaGeometrica{

    private double x;
    private double y;
    private double tamLado;

    public TrianguloEquilatero(double x, double y, double tamLado){
        this.x = x;
        this.y = y;
        this.tamLado = tamLado;
    }

    public double CalculaPerimetro() {
        return 3*tamLado;
    }

    public double CalculaArea() {
        return (Math.sqrt(3)*tamLado*tamLado)/4;
    }

    @Override
    public String toString() {
        return "TrianguloEquilatero{" +
                "x=" + x +
                ", y=" + y +
                ", tamLado=" + tamLado +
                '}';
    }
}
