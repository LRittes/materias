package dados;

public class Trapezio extends FormaGeometrica{
    private int altura;

    public void setBaseMenor(int valor){
        this.medida1 = valor;
    }
    public void setBaseMaior(int valor){
        this.medida2 = valor;
    }
    public int getAltura() {
        return altura;
    }
    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int calcularArea(){
        return ((medida2 - medida1) * altura)/2;
    }

    public int calcularPerimetro(){
        return (int) ((int) 4*Math.pow((getMedida2() - getMedida1())/2,0.5));
    }
    
    @Override
    public String toString(){
        String str = "";
        str += "Base menor: " + getMedida1();
        str += "\nBase maior: " + getMedida2();
        str += "\nPerimetro: " + calcularPerimetro();
        str += "\nArea: "+ calcularArea();
        return str;
    }
}
