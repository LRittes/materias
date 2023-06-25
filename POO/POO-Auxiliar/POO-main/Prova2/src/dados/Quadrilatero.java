package dados;

public abstract class Quadrilatero extends FormaGeometrica{

    protected double x;
    protected double y;
    protected double tam4lados;

    public Quadrilatero(double x, double y, double tam4lados){
        this.x = x;
        this.y = y;
        this.tam4lados = tam4lados;
    }
    public Quadrilatero(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double CalculaPerimetro() {
        return 4 * tam4lados;
    }
}
