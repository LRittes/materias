package dados;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SistemaProcessos {
    List<Juiz> juizes = new ArrayList<>();
    List<Processo> processos = new ArrayList<>();
    Random gerador = new Random();

    public void cadastrarProcessos(Processo processo) {
        processos.add(processo);
    }

    public void cadastrarJuiz(Juiz juiz) {
        juizes.add(juiz);
    }

    public void ditribuirProcessos() {

        for (int i = 0; i < processos.size(); i++) {
            try {
                if (sortearJuiz(processos.get(i))) {
                    continue;
                } else {
                    throw new ProcessoSemJuizException();
                }
            } catch (ProcessoSemJuizException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean sortearJuiz(Processo processo) {
        List<Integer> sorteados = new ArrayList<>();
        while (sorteados.size() != juizes.size()) {
            int sorteado = gerador.nextInt(juizes.size());
            if (!naoSorteado(sorteado, sorteados)) {
                if (juizes.get(sorteado).getProcessos().size() < juizes.get(sorteado).getLimite()) {
                    juizes.get(sorteado).cadastrarProcesso(processo);
                    return true;
                }
                sorteados.add(sorteado);
            }
        }
        return false;
    }

    private boolean naoSorteado(int n, List<Integer> lista) {
        for (int i : lista) {
            if (i == n)
                return true;
        }
        return false;
    }
}
