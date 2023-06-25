package dados;

public abstract class Circulo extends FormaGeometrica{

    public void setRaio(int valor){
        this.medida1 = valor;
    }

    public int calcularPerimetro(){
        return (int) (Math.PI * 2 * medida1);
    }

    public int calcularArea(){
        return (int) (Math.PI * Math.pow(medida1, 2));
    }

    public String toString() {
        String str = "";
        str += "Raio: " + getMedida1();
        str += "\nArea: " + calcularPerimetro();
        str += "\nPerimetro: " + calcularPerimetro();
        return str;
    }

}