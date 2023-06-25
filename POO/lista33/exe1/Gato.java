package exe1;

public class Gato extends Animal {
    public Gato() {
        this.setNome("Gato");
    }

    @Override
    public String emitirSom() {

        return this.getNome() + ": Miau!";
    }
}
