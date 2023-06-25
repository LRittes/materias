package negocio;

import dados.FormaGeometrica;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorFormasGeometricas {

    private List<FormaGeometrica> listaPoligonos = new ArrayList<FormaGeometrica>();

    public void inserirFormaGeomatrica(FormaGeometrica forma){
        listaPoligonos.add(forma);
    }

    public List<FormaGeometrica> getListaPoligonos() {
        return listaPoligonos;
    }

}
