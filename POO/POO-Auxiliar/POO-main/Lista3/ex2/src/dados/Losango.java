package dados;

public class Losango extends FormaGeometrica{
    
    public void setD(int valor){
        this.medida1 = valor;
    }

    public void setd(int valor){
        this.medida2 = valor;
    }

    public int calcularArea(){
        return (medida2*medida1)/2;
    }

    public int calcularPerimetro(){
        return (int) (4*Math.pow(Math.pow(medida1,2)/4 + Math.pow(medida2,2)/4 ,0.5));
    }

    public String toString() {
        String str = "";
        str += "Diagonal maior: " + getMedida2();
        str += "\nDiagonal menor: " + getMedida1();
        str += "\nArea: " + calcularArea();
        str += "\nPerietro: " + calcularPerimetro();
        return str;
    }
    
}
