package dados;

public abstract class FormaGeometrica{
    protected int medida1;
    protected int medida2;

    public int calcularArea(){
        return 0;
    }

    public int calcularPerimetro(){
        return 0;
    }

    public int getMedida1() {
        return medida1;
    }
    public void setMedida1(int medida1) {
        this.medida1 = medida1;
    }
    public int getMedida2() {
        return medida2;
    }
    public void setMedida2(int medida2) {
        this.medida2 = medida2;
    }
   
}
