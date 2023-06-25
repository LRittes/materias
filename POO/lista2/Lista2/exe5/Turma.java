
import java.util.Collections;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private List<Aluno> aluno = new ArrayList<Aluno>();

    public void adicionaralunoo(Aluno alu) {
        aluno.add(alu);
    }

    private void ordenarPorMedia() {
        Collections.sort(aluno);
    }

    public List<Equipe<Aluno>> separarEmEquipes() {
        ordenarPorMedia();
        int size = aluno.size();
        int div4, div3, posi = 0, c = 1;

        List<Equipe<Aluno>> equipes = new ArrayList<Equipe<Aluno>>();
        Equipe<Aluno> equipe;

        // Número de elementos por grupo
        if (size % 4 == 0) {
            div4 = size / 4;
            for (int i = 0; i < div4; i++) {
                equipe = new Equipe<Aluno>();
                equipe.setNome("Equipe " + c);

                equipe.getEquipe().add(aluno.get(posi));
                posi++;
                equipe.getEquipe().add(aluno.get(posi));
                posi++;

                size--;
                equipe.getEquipe().add(aluno.get(size));
                size--;
                equipe.getEquipe().add(aluno.get(size));
                size--;

                c++;
                equipes.add(equipe);
            }
        } else {
            div4 = size / 4;
            div3 = (size % 4) / 3;

            for (int i = 0; i < div4; i++) {
                equipe = new Equipe<Aluno>();
                equipe.setNome("Equipe " + c);

                equipe.getEquipe().add(aluno.get(posi));
                posi++;
                equipe.getEquipe().add(aluno.get(posi));
                posi++;

                size--;
                equipe.getEquipe().add(aluno.get(size));
                size--;
                equipe.getEquipe().add(aluno.get(size));
                size--;

                c++;
                equipes.add(equipe);
            }

            for (int i = 0; i < div3; i++) {
                equipe = new Equipe<Aluno>();
                equipe.setNome("Equipe " + c);

                equipe.getEquipe().add(aluno.get(posi));
                posi++;
                equipe.getEquipe().add(aluno.get(posi));
                posi++;

                size--;
                equipe.getEquipe().add(aluno.get(size));
                size--;

                c++;
                equipes.add(equipe);
            }

        }

        return equipes;
    }

    public List<Aluno> retornaralunos() {
        return aluno;
    }
}
