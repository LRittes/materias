package exe1;

public class Cachorro extends Animal {
    public Cachorro() {
        this.setNome("Cachorro");
    }

    @Override
    public String emitirSom() {

        return this.getNome() + ": Au Au!";
    }
}
