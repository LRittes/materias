package exe2;

public class TrianguloEquilatero extends FormaGeometrica {

    public TrianguloEquilatero(int medidaLado) {
        medida1 = medidaLado;
    }

    @Override
    public double calcularArea() {
        return ((medida1 * medida1) * Math.pow(3, 0.5)) / 4;
    }

    @Override
    public double calcularPerimetro() {
        return 3 * medida1;
    }

    @Override
    public String toString() {
        return "Triangulo Equilatero: Distancia maior: " + medida1 + " Distancia menor: " + medida2;

    }

}
