package negocio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import dados.Circulo;
import dados.FormaGeometrica;
import dados.Quadrado;
import dados.Retangulo;
import dados.TrianguloEquilatero;

public class GerenciadorFormasGeometricas {
    private List<FormaGeometrica> listaForm = new ArrayList<>();

    public GerenciadorFormasGeometricas() {
        this.leTexto("negocio/arq/db.csv");
    }

    public List<FormaGeometrica> getListaForm() {
        return listaForm;
    }

    public void addForms(FormaGeometrica f) {
        listaForm.add(f);
        this.gravaTexto("negocio/arq/db.csv");
    }

    public void leTexto(String path) {
        FileReader reader;
        BufferedReader lerArq;

        try {
            reader = new FileReader(path);
            lerArq = new BufferedReader(reader);
            String s = lerArq.readLine();

            while (s != null) {
                List<String> listStr = List.of(s.split(";"));
                FormaGeometrica form = new Circulo(0, 0, 0);
                switch (listStr.get(0)) {
                    case "Circulo":
                        form = new Circulo(Double.parseDouble(listStr.get(1)),
                                Double.parseDouble(listStr.get(2)),
                                Double.parseDouble(listStr.get(3)));

                        break;
                    case "Quadrado":
                        form = new Quadrado(Double.parseDouble(listStr.get(1)),
                                Double.parseDouble(listStr.get(2)),
                                Double.parseDouble(listStr.get(3)));
                        break;
                    case "TrianguloEquilatero":
                        form = new TrianguloEquilatero(Double.parseDouble(listStr.get(1)),
                                Double.parseDouble(listStr.get(2)),
                                Double.parseDouble(listStr.get(3)));
                        break;
                    case "Retangulo":
                        form = new Retangulo(Double.parseDouble(listStr.get(1)),
                                Double.parseDouble(listStr.get(2)),
                                Double.parseDouble(listStr.get(3)), Double.parseDouble(listStr.get(4)));
                        break;

                    default:
                        break;
                }
                listaForm.add(form);
                s = lerArq.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.err.print(e.getMessage());
            System.exit(0);
        }
    }

    public void gravaTexto(String path) {
        FileWriter arq;
        try {
            arq = new FileWriter(path);
            for (int i = 0; i < this.listaForm.size(); i++) {
                arq.write(toCSV(this.listaForm.get(i)) + '\n');
            }
            arq.close();
        } catch (Exception e) {
            System.err.print("Erro ao manipular o arquivo");
            System.exit(0);
        }

    }

    public String toCSV(FormaGeometrica f) {
        return f.type() + ";" + f.getX() + ";" + f.getY() + ";" + f.props();
    }

}
