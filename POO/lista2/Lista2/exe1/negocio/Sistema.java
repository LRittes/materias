package negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dados.Pessoa;

public class Sistema {
    private Map<Integer, List<Pessoa>> mapLista = new HashMap<Integer, List<Pessoa>>();

    public Sistema() {
        List<Pessoa> list;

        for (int i = 1; i <= 5; i++) {
            list = new ArrayList<Pessoa>();
            mapLista.put(i, list);
        }
    }

    public void adicionarPessoa(Pessoa p) {
        if (p.getIdade() >= 1 && p.getIdade() <= 12) {
            List<Pessoa> people = mapLista.get(1);
            people.add(p);
            sort(1);
        }

        if (p.getIdade() >= 13 && p.getIdade() <= 18) {
            List<Pessoa> people = mapLista.get(2);
            people.add(p);
            sort(2);
        }

        if (p.getIdade() >= 19 && p.getIdade() <= 25) {
            List<Pessoa> people = mapLista.get(3);
            people.add(p);
            sort(3);
        }

        if (p.getIdade() >= 26 && p.getIdade() <= 59) {
            List<Pessoa> people = mapLista.get(4);
            people.add(p);
            sort(4);
        }

        if (p.getIdade() >= 60) {
            List<Pessoa> people = mapLista.get(5);
            people.add(p);
            sort(5);
        }
    }

    public void sort(int n) {
        List<Pessoa> lista = mapLista.get(n);
        Collections.sort(lista);
    }

    public Map<Integer, List<Pessoa>> mapPessoas() {
        return mapLista;
    }
}
