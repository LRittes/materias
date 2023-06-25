package exe1;

public class Vaca extends Animal {

    public Vaca() {
        this.setNome("Vaca");
    }

    @Override
    public String emitirSom() {

        return this.getNome() + ": Muuuuu!";
    }

}
