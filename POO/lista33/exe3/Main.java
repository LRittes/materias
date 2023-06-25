package exe3;

public class Main {
    public static void main(String[] args) {
        Grafo gra = new Grafo();
        System.out.println(gra.toString());
        gra.addVertice();
        System.out.println(gra.toString());
        gra.addVertice();
        System.out.println(gra.toString());
        gra.addAresta(0, 1);
        System.out.println(gra.toString());
        gra.addVertice();
        System.out.println(gra.toString());
        System.out.println(gra.toString());

    }
}
