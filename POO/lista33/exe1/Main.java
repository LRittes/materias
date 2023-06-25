package exe1;

public class Main {
    public static void main(String[] args) {
        Cachorro dog = new Cachorro();
        Vaca vaca = new Vaca();
        Gato gato = new Gato();

        System.out.println(dog.emitirSom());
        System.out.println(gato.emitirSom());
        System.out.println(vaca.emitirSom());

    }
}
