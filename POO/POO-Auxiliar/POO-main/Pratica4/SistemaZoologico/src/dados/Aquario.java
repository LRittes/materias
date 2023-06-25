package dados;

public class Aquario extends Viveiro{

    private float altura;
    private float temperatura;

    public float calculaEspaco(){
        return altura*comprimento*largura;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

}