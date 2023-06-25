package apresentacao;

import dados.*;

public class Main {
    public static void main(String[] args) {
        SistemaProcessos sitema = new SistemaProcessos();

        Juiz juiz = new Juiz(6);
        juiz.setNome("Paulo");
        sitema.cadastrarJuiz(juiz);

        for (int i = 0; i < 6; i++) {
            Processo aux = new Processo();
            aux.setId(i);
            aux.setNome("nome do processo " + i + " : " + i);
            sitema.cadastrarProcessos(aux);
        }

        sitema.ditribuirProcessos();

    }
}
